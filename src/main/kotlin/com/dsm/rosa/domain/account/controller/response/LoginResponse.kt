package com.dsm.rosa.domain.account.controller.response

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accountName: String,
)