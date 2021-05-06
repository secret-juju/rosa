package com.dsm.rosa.domain.account.service

import com.dsm.rosa.global.security.provider.TokenProvider
import org.springframework.stereotype.Service

@Service
class AuthenticationSearchService(
    private val tokenProvider: TokenProvider,
) {

    fun getAuthenticationData(token: String) =
        tokenProvider.getData(token)
}