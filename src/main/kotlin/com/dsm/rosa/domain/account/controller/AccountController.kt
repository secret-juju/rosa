package com.dsm.rosa.domain.account.controller

import com.dsm.rosa.domain.account.controller.response.LoginResponse
import com.dsm.rosa.domain.account.service.AccountRegistrationService
import com.dsm.rosa.domain.account.service.AuthenticationService
import com.dsm.rosa.global.attribute.OAuth2Type
import org.springframework.web.bind.annotation.*

@RestController
class AccountController(
    private val accountRegistrationService: AccountRegistrationService,
    private val authenticationService: AuthenticationService,
) {

    @PostMapping("/login/oauth2/code/{oauth2-type}")
    fun login(
        @RequestHeader("oauth2-token")
        oAuth2Token: String,
        @PathVariable("oauth2-type")
        oAuth2Type: OAuth2Type,
    ): LoginResponse {
        val accountInformation = accountRegistrationService.registerAccount(
            oAuth2Token = oAuth2Token,
            oAuth2Type = oAuth2Type,
        )

        return LoginResponse(
            accessToken = authenticationService.createAccessToken(accountInformation.accountEmail),
            refreshToken = authenticationService.createRefreshToken(accountInformation.accountEmail),
            accountName = accountInformation.accountName,
        )
    }
}