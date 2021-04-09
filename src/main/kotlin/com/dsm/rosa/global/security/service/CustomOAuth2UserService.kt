package com.dsm.rosa.global.security.service

import com.dsm.rosa.domain.account.domain.User
import com.dsm.rosa.domain.account.exception.OAuth2AuthenticationProcessingException
import com.dsm.rosa.domain.account.repository.UserRepository
import com.dsm.rosa.global.security.dto.AuthProvider
import com.dsm.rosa.global.security.user.OAuth2UserInfo
import com.dsm.rosa.global.security.user.OAuth2UserInfoFactory
import com.dsm.rosa.global.security.user.UserPrincipal
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service


@Service
class CustomOAuth2UserService(
    val userRepository: UserRepository,
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)

        return try {
            processOAuth2User(
                oAuth2UserRequest = userRequest,
                oAuth2User = oAuth2User
            )
        } catch (ex: AuthenticationException) {
            throw ex
        } catch (ex: Exception) {
            throw InternalAuthenticationServiceException(ex.message, ex.cause)
        }
    }

    private fun processOAuth2User(oAuth2UserRequest: OAuth2UserRequest, oAuth2User: OAuth2User): OAuth2User {
        val oAuth2UserInfo: OAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
            oAuth2UserRequest.clientRegistration.registrationId,
            oAuth2User.attributes
        )

        if (oAuth2UserInfo.email.isEmpty()) {
            throw OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider")
        }

        val user: User = userRepository.findByEmail(oAuth2UserInfo.email)
            ?: registerUser(oAuth2UserRequest, oAuth2UserInfo)

        return UserPrincipal.create(user, oAuth2User.attributes)
    }

    private fun registerUser(oAuth2UserRequest: OAuth2UserRequest, oAuth2UserInfo: OAuth2UserInfo): User {
        val user = User(
            email = oAuth2UserInfo.email,
            name = oAuth2UserInfo.name,
            provider = AuthProvider.valueOf(oAuth2UserRequest.clientRegistration.registrationId),
            providerId = oAuth2UserInfo.id,
        )
        return userRepository.save(user)
    }
}