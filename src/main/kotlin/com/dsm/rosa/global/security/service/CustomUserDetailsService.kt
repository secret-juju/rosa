package com.dsm.rosa.global.security.service

import com.dsm.rosa.domain.account.exception.UserNotFoundException
import com.dsm.rosa.domain.account.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(email: String) =
        userRepository.findByEmail(email) ?: throw UserNotFoundException(email)
}