package com.dsm.rosa.domain.account.external

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleOAuth2AuthenticationResponse(
    val name: String,
    val email: String,
)