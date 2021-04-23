package com.dsm.rosa.domain.account.external.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class FacebookOAuth2AuthenticationResponse(
    val data: FacebookResponse,
) {

    data class FacebookResponse(
        @JsonProperty("user_id")
        val userId: String,
    )
}