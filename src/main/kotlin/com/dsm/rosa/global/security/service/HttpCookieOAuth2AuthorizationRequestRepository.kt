package com.dsm.rosa.global.security.service

import com.dsm.rosa.domain.account.exception.OAuth2AuthenticationProcessingException
import com.dsm.rosa.global.security.provider.CookieProvider
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request"
const val REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri"
private const val cookieExpireSeconds = 180

@Component
class HttpCookieOAuth2AuthorizationRequestRepository : AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    override fun loadAuthorizationRequest(request: HttpServletRequest): OAuth2AuthorizationRequest {
        val cookie = CookieProvider.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
            ?: throw OAuth2AuthenticationProcessingException("")
        return CookieProvider.deserialize(cookie, OAuth2AuthorizationRequest::class.java)
    }

    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest?,
        request: HttpServletRequest,
        response: HttpServletResponse,
    ) {
        if (authorizationRequest == null) {
            CookieProvider.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
            CookieProvider.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME)
            return
        }
        CookieProvider.addCookie(
            response,
            OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME,
            CookieProvider.serialize(authorizationRequest),
            cookieExpireSeconds
        )
        val redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME)
        CookieProvider.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, cookieExpireSeconds)
    }

    override fun removeAuthorizationRequest(request: HttpServletRequest?): OAuth2AuthorizationRequest {
        return loadAuthorizationRequest(request!!)
    }

    fun removeAuthorizationRequestCookies(request: HttpServletRequest, response: HttpServletResponse) {
        CookieProvider.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
        CookieProvider.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME)
    }
}