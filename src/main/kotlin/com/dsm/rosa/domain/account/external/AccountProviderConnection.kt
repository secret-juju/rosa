package com.dsm.rosa.domain.account.external

import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountSearchConnection {

    @Headers(value = ["accept: application/json", "content-type: application/json"])
    @GET("/oauth2/v3/tokeninfo")
    fun authenticateFromGoogle(@Query("id_token") tokenId: String)
}