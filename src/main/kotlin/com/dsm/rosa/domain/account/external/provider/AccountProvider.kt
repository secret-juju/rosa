package com.dsm.rosa.domain.account.external.provider

import com.dsm.rosa.domain.account.external.response.OAuth2AuthenticationResponse

interface AccountProvider {
    fun searchAccount(oAuth2Token: String): OAuth2AuthenticationResponse
}