package com.dsm.rosa.domain.account.service

import com.dsm.rosa.global.attribute.Token
import com.dsm.rosa.global.exception.InvalidTokenException
import com.dsm.rosa.global.security.provider.TokenProvider
import org.springframework.stereotype.Service

@Service
class AuthenticationValidationService(
    private val tokenProvider: TokenProvider,
) {

    fun validateBothToken(accessToken: String, refreshToken: String) {
        val isValidAccessToken = validateToken(accessToken, Token.ACCESS)
        val isValidRefreshToken = validateToken(refreshToken, Token.REFRESH)

        if (!(isValidAccessToken && isValidRefreshToken))
            throw InvalidTokenException()
    }

    fun validateToken(token: String, tokenType: Token): Boolean {
        val isValidToken = tokenProvider.isToken(
            token = token,
            tokenType = tokenType,
        )

        if (!isValidToken)
            throw InvalidTokenException()

        return isValidToken
    }
}