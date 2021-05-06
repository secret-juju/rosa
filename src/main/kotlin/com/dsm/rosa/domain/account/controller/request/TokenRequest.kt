package com.dsm.rosa.domain.account.controller.request

import javax.validation.constraints.NotBlank

data class TokenRequest(
    @get:NotBlank(message = "<NULL> <EMPTY> <BLANK>")
    val accessToken: String,
    @get:NotBlank(message = "<NULL> <EMPTY> <BLANK>")
    val refreshToken: String,
)