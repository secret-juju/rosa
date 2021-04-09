package com.dsm.rosa.domain.account.controller

data class LoginRequest(
    val email: String,
    val password: String,
)