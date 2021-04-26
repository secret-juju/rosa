package com.dsm.rosa.domain.account.external.provider

import com.dsm.rosa.domain.account.external.connection.AccountProviderConnection
import com.dsm.rosa.domain.account.external.response.OAuth2AuthenticationResponse
import com.dsm.rosa.global.security.exception.InvalidTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.yaml.snakeyaml.Yaml
import java.io.FileReader

class FacebookAccountProvider(
    private val accountProviderConnection: AccountProviderConnection,
) : AccountProvider {

    override fun searchAccount(oAuth2Token: String): OAuth2AuthenticationResponse {
        try (val reader = FileReader("../../../../../../../../resources/application.yml")) {
            val yml = Yaml().load(reader)

        }

        val facebookResponse =
            accountProviderConnection.authenticateFromFacebook(
                inputToken = oAuth2Token,
                accessToken =
            ).execute()
                .body()
                ?: throw InvalidTokenException()

        return OAuth2AuthenticationResponse(
            accountId = facebookResponse.data.userId,
            accountName = facebookResponse.data.userId,
        )
    }
}