package com.dsm.rosa.domain.account.external.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NaverOAuth2AuthenticationResponse(
    val response: NaverResponse,
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class NaverResponse(
        val id: String,
        val name: String,
    )
}