package com.dsm.rosa.domain.account.controller

import org.springframework.http.ResponseEntity

import org.springframework.web.servlet.support.ServletUriComponentsBuilder

import org.springframework.web.bind.annotation.RequestBody

import javax.validation.Valid

import org.springframework.web.bind.annotation.PostMapping

import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import com.dsm.rosa.global.security.user.TokenProvider

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.security.crypto.password.PasswordEncoder

import com.dsm.rosa.domain.account.repository.UserRepository

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication

import org.springframework.web.bind.annotation.RequestMapping

import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController(
    val authenticationManager: AuthenticationManager,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val tokenProvider: TokenProvider,
) {

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest): ResponseEntity<*> {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.email,
                loginRequest.password,
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val token = tokenProvider.createToken(authentication)
        return ResponseEntity.ok<Any>(AuthResponse(token))
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: @Valid SignUpRequest?): ResponseEntity<*> {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw BadRequestException("Email address already in use.")
        }
        // Creating user's account
        val user = User()
        user.setName(signUpRequest.getName())
        user.setEmail(signUpRequest.getEmail())
        user.setPassword(signUpRequest.getPassword())
        user.setProvider(AuthProvider.local)
        user.setPassword(passwordEncoder!!.encode(user.getPassword()))
        val result: User = userRepository!!.save(user)
        val location: URI = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/user/me")
            .buildAndExpand(result.getId()).toUri()
        return ResponseEntity.created(location)
            .body<Any>(ApiResponse(true, "User registered successfully@"))
    }
}