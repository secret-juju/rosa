package com.dsm.rosa.domain.account.external

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OAuth2AuthenticationResponse(

    @JsonProperty("name")
    val accountName: String,

    @JsonProperty("email")
    val accountEmail: String,
)