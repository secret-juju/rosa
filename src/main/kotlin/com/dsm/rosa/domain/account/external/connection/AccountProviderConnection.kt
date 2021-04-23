package com.dsm.rosa.domain.account.external.connection

import com.dsm.rosa.domain.account.external.response.FacebookOAuth2AuthenticationResponse
import com.dsm.rosa.domain.account.external.response.GoogleOAuth2AuthenticationResponse
import com.dsm.rosa.domain.account.external.response.NaverOAuth2AuthenticationResponse
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AccountProviderConnection {

    @Headers(value = ["accept: application/json", "content-type: application/json"])
    @GET("/oauth2/v3/tokeninfo")
    fun authenticateFromGoogle(
        @Query("id_token") tokenId: String,
    ): Call<GoogleOAuth2AuthenticationResponse>

    @Headers(value = ["accept: application/json", "content-type: application/json"])
    fun authenticateFromFacebook(
        @Query("input_token") inputToken: String,
        @Query("access_token") accessToken: String,
    ): Call<FacebookOAuth2AuthenticationResponse>

    @Headers(value = ["accept: application/json", "content-type: application/json"])
    @GET("/v1/nid/me")
    fun authenticateFromNaver(
        @Header("Authorization") authorization: String,
    ): Call<NaverOAuth2AuthenticationResponse>
}