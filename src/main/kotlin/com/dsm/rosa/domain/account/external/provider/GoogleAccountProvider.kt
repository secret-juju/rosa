package com.dsm.rosa.domain.account.external.provider

import com.dsm.rosa.domain.account.external.connection.AccountProviderConnection
import com.dsm.rosa.domain.account.external.response.OAuth2AuthenticationResponse
import com.dsm.rosa.global.security.exception.InvalidTokenException

class GoogleAccountProvider(
    private val accountProviderConnection: AccountProviderConnection,
) : AccountProvider {

    override fun searchAccount(oAuth2Token: String): OAuth2AuthenticationResponse {
        val googleResponse =
            accountProviderConnection.authenticateFromGoogle(oAuth2Token)
                .execute()
                .body()
                ?: throw InvalidTokenException()

        return OAuth2AuthenticationResponse(
            accountId = googleResponse.email,
            accountName = googleResponse.name,
        )
    }
}