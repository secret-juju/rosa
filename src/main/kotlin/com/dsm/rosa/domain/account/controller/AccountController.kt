package com.dsm.rosa.domain.account.controller

import com.dsm.rosa.domain.account.controller.request.TokenRequest
import com.dsm.rosa.domain.account.controller.response.LoginResponse
import com.dsm.rosa.domain.account.controller.response.TokenResponse
import com.dsm.rosa.domain.account.service.AccountRegistrationService
import com.dsm.rosa.domain.account.service.AuthenticationCreationService
import com.dsm.rosa.domain.account.service.AuthenticationSearchService
import com.dsm.rosa.domain.account.service.AuthenticationValidationService
import com.dsm.rosa.global.attribute.OAuth2Type
import com.dsm.rosa.global.attribute.Token
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
class AccountController(
    private val accountRegistrationService: AccountRegistrationService,
    private val authenticationCreationService: AuthenticationCreationService,
    private val authenticationValidationService: AuthenticationValidationService,
    private val authenticationSearchService: AuthenticationSearchService,
) {

    @PostMapping("/login/oauth2/code/{oauth2-type}")
    fun login(
        @RequestHeader("Authorization")
        oAuth2Token: String,
        @PathVariable("oauth2-type")
        oAuth2Type: OAuth2Type,
    ): LoginResponse {
        val accountInformation = accountRegistrationService.registerAccount(
            oAuth2Token = oAuth2Token,
            oAuth2Type = oAuth2Type,
        )

        return LoginResponse(
            accessToken = authenticationCreationService.createAccessToken(accountInformation.accountId),
            refreshToken = authenticationCreationService.createRefreshToken(accountInformation.accountId),
            accountName = accountInformation.accountName,
        )
    }

    @PostMapping("/auth/token")
    fun reissueAccessToken(
        @NotNull
        @Valid
        @RequestBody
        request: TokenRequest,
    ): TokenResponse {
        authenticationValidationService.validateBothToken(
            accessToken = request.accessToken,
            refreshToken = request.refreshToken,
        )

        return TokenResponse(
            token = authenticationCreationService.createAccessToken(
                accountEmail = authenticationSearchService.getAuthenticationData(
                    token = request.accessToken
                )
            )
        )
    }
}