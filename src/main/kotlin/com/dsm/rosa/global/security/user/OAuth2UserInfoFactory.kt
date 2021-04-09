package com.dsm.rosa.global.security.user

import com.dsm.rosa.domain.account.exception.OAuth2AuthenticationProcessingException
import com.dsm.rosa.global.security.dto.AuthProvider

object OAuth2UserInfoFactory {
    fun getOAuth2UserInfo(registrationId: String, attributes: Map<String, Any>): OAuth2UserInfo {
        return if (registrationId.equals(AuthProvider.google.toString(), ignoreCase = true)) {
            GoogleOAuth2UserInfo(attributes)
        } else if (registrationId.equals(AuthProvider.facebook.toString(), ignoreCase = true)) {
            FacebookOAuth2UserInfo(attributes)
        } else if (registrationId.equals(AuthProvider.naver.toString(), ignoreCase = true)) {
            NaverOAuth2UserInfo(attributes)
        } else {
            throw OAuth2AuthenticationProcessingException("")
        }
    }
}