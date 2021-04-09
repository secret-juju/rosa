package com.dsm.rosa.domain.account.external

interface AccountProvider {
    fun searchAccount(authorization: String): OAuth2AuthenticationResponse
}