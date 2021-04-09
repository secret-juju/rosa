package com.dsm.rosa.domain.account.external

import com.dsm.rosa.global.attribute.OAuth2Type
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object AccountProviderFactory {
    private val oAuth2Connection = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com")
        .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
        .build()
        .create(AccountProviderConnection::class.java)

    fun getAccountProvider(oAuth2Type: OAuth2Type) =
        when (oAuth2Type) {
            OAuth2Type.GOOGLE -> GoogleAccountProvider(oAuth2Connection)
            OAuth2Type.FACEBOOK -> throw Exception()
            OAuth2Type.NAVER -> throw Exception()
        }
}