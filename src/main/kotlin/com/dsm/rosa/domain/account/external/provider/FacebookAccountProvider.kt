package com.dsm.rosa.domain.account.external.provider

import com.dsm.rosa.domain.account.external.connection.AccountProviderConnection
import com.dsm.rosa.domain.account.external.response.OAuth2AuthenticationResponse
import com.dsm.rosa.global.security.exception.InvalidTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.util.FileCopyUtils
import org.yaml.snakeyaml.Yaml
import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter
import java.nio.charset.StandardCharsets

class FacebookAccountProvider(
    private val accountProviderConnection: AccountProviderConnection,
) : AccountProvider {

    override fun searchAccount(oAuth2Token: String): OAuth2AuthenticationResponse {
        val facebookAccessKey: String
        FileReader(ClassPathResource("application.yml").file.path).use {
            val yml = Yaml().load<Map<String, Any>>(it)
            facebookAccessKey = ((yml["auth"] as Map<*, *>)["oauth2"] as Map<*, *>)["facebook-access-key"] as String
        }
        println("facebook access key :::::::: $facebookAccessKey")


        val facebookResponse =
            accountProviderConnection.authenticateFromFacebook(
                inputToken = oAuth2Token,
                accessToken = facebookAccessKey,
            ).execute()
                .body()
                ?: throw InvalidTokenException()

        return OAuth2AuthenticationResponse(
            accountId = facebookResponse.data.userId,
            accountName = facebookResponse.data.userId,
        )
    }
}