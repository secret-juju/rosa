package com.dsm.rosa.domain.account.external.provider

import com.dsm.rosa.domain.account.external.connection.AccountProviderConnection
import com.dsm.rosa.domain.account.external.response.OAuth2AuthenticationResponse
import com.dsm.rosa.global.security.exception.InvalidTokenException
import org.springframework.core.io.ClassPathResource
import org.springframework.util.FileCopyUtils
import org.yaml.snakeyaml.Yaml
import java.io.FileReader
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

        val classPathResource = ClassPathResource("application.yml")
        val byteData = FileCopyUtils.copyToByteArray(classPathResource.inputStream)
        val yml = String(byteData, StandardCharsets.UTF_8)
        println(yml)

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