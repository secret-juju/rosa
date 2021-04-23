package com.dsm.rosa.domain.account.external.provider

import com.dsm.rosa.domain.account.external.connection.AccountProviderConnection
import com.dsm.rosa.domain.account.external.response.OAuth2AuthenticationResponse
import com.dsm.rosa.global.security.exception.InvalidTokenException
import org.springframework.beans.factory.annotation.Value

class FacebookAccountProvider(
    @Value("\${auth.oauth2.facebook-access-token}")
    private val accessToken: String,
    private val accountProviderConnection: AccountProviderConnection,
) : AccountProvider {

    override fun searchAccount(oAuth2Token: String): OAuth2AuthenticationResponse {
        val facebookResponse =
            accountProviderConnection.authenticateFromFacebook(
                inputToken = oAuth2Token,
                accessToken = accessToken,
            ).execute()
                .body()
                ?: throw InvalidTokenException()

        return OAuth2AuthenticationResponse(
            accountId = facebookResponse.data.userId,
            accountName = facebookResponse.data.userId,
        )
    }
}