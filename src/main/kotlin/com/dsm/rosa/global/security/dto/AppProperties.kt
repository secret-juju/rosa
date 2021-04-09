package com.dsm.rosa.global.security.dto

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "app")
data class AppProperties(
    private val tokenSecret: String,
    private val tokenExpirationMsec: Long,
    private val authorizedRedirectUris: List<String>,
) {

    fun getAuth() = Auth(tokenSecret, tokenExpirationMsec)
    fun getOAuth2() = OAuth2(authorizedRedirectUris)

    data class Auth(val tokenSecret: String, val tokenExpirationMsec: Long)
    data class OAuth2(val authorizedRedirectUris: List<String>)
}