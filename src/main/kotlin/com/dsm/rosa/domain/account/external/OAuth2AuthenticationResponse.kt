package com.dsm.rosa.domain.account.external

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.Column

@JsonIgnoreProperties(ignoreUnknown = true)
data class OAuth2AuthenticationResponse(

    @Column(name = "name")
    val accountName: String,

    @Column(name = "email")
    val accountEmail: String,
)