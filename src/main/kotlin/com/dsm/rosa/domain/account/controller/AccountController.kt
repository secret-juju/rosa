package com.dsm.rosa.domain.account.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {

    @GetMapping("/user")
    fun test(@AuthenticationPrincipal principal: AuthenticationPrincipal) {

    }
}