package com.dsm.rosa.domain.account.service

import com.dsm.rosa.global.attribute.Token
import com.dsm.rosa.global.security.provider.TokenProvider
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    val tokenProvider: TokenProvider,
) {

    fun createAccessToken(accountEmail: String) =
        tokenProvider.createToken(accountEmail, Token.ACCESS)

    fun createRefreshToken(accountEmail: String) =
        tokenProvider.createToken(accountEmail, Token.REFRESH)
}