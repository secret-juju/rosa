package com.dsm.rosa.global.security.user

import com.dsm.rosa.global.security.dto.AppProperties
import com.dsm.rosa.global.security.provider.CookieProvider
import com.dsm.rosa.global.security.service.HttpCookieOAuth2AuthorizationRequestRepository
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2AuthenticationSuccessHandler(
    val tokenProvider: TokenProvider,
    val appProperties: AppProperties,
    val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository,
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val targetUrl = determineTargetUrl(request, response, authentication)
        if (response.isCommitted) {
            logger.debug("Response has already been committed. Unable to redirect to $targetUrl")
            return
        }
        clearAuthenticationAttributes(request, response)
        redirectStrategy.sendRedirect(request, response, targetUrl)
    }

    override fun determineTargetUrl(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ): String {
        val redirectUri: String = CookieProvider.getCookie(request, "oauth2-cookie")!!.value

//        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication")
//        }
        val token: String = tokenProvider.createToken(authentication)
        return UriComponentsBuilder.fromUriString(redirectUri)
            .queryParam("token", token)
            .build().toUriString()
    }

    protected fun clearAuthenticationAttributes(request: HttpServletRequest, response: HttpServletResponse) {
        super.clearAuthenticationAttributes(request)
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response)
    }

    private fun isAuthorizedRedirectUri(uri: String): Boolean {
        val clientRedirectUri: URI = URI.create(uri)
        return appProperties.getOAuth2().authorizedRedirectUris
            .stream()
            .anyMatch { authorizedRedirectUri ->
                // Only validate host and port. Let the clients use different paths if they want to
                val authorizedURI: URI = URI.create(authorizedRedirectUri)
                if (authorizedURI.host == clientRedirectUri.host
                    && authorizedURI.port === clientRedirectUri.port
                ) {
                    return@anyMatch true
                }
                false
            }
    }
}