package com.dsm.rosa.domain.account.external

import com.dsm.rosa.domain.account.external.provider.GoogleAccountProvider
import com.dsm.rosa.global.attribute.OAuth2Type
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object AccountProviderFactory {
    fun getAccountProvider(oAuth2Type: OAuth2Type) =
        when (oAuth2Type) {
            OAuth2Type.GOOGLE -> GoogleAccountProvider(createConnection(oAuth2Type.baseUrl))
            OAuth2Type.FACEBOOK -> throw Exception()
            OAuth2Type.NAVER -> throw Exception()
        }

    fun createConnection(baseUrl: String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
            .build()
            .create(AccountProviderConnection::class.java)
}