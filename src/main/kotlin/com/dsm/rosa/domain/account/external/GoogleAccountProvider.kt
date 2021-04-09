package com.dsm.rosa.domain.account.external

import com.dsm.rosa.global.security.exception.InvalidTokenException

class GoogleAccountProvider(
    private val accountProviderConnection: AccountProviderConnection,
) : AccountProvider {

    override fun searchAccount(oAuth2Token: String) =
        accountProviderConnection.authenticateFromGoogle(
            tokenId = oAuth2Token,
        ).execute().body()
            ?: throw InvalidTokenException()
}