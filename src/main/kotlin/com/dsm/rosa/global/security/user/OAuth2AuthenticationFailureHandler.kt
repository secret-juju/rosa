package com.dsm.rosa.global.security.user

import com.dsm.rosa.global.security.provider.CookieProvider
import org.springframework.web.util.UriComponentsBuilder

import javax.servlet.ServletException

import java.io.IOException

import javax.servlet.http.HttpServletResponse

import javax.servlet.http.HttpServletRequest

import com.dsm.rosa.global.security.service.HttpCookieOAuth2AuthorizationRequestRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.AuthenticationException

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component


@Component
class OAuth2AuthenticationFailureHandler(
    val httpCookieOAuth2AuthorizationRequestRepository: HttpCookieOAuth2AuthorizationRequestRepository,
) : SimpleUrlAuthenticationFailureHandler() {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException,
    ) {
        var targetUrl: String = CookieProvider.getCookie(request, "oauth2-cookie")!!.value
        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("error", exception.localizedMessage)
            .build().toUriString()
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response)
        redirectStrategy.sendRedirect(request, response, targetUrl)
    }
}