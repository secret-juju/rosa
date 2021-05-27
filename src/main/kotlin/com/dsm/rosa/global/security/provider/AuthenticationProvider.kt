package com.dsm.rosa.global.security.provider

import com.dsm.rosa.domain.account.domain.Account
import com.dsm.rosa.domain.account.repository.AccountRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import javax.security.auth.login.AccountNotFoundException

@Component
class AuthenticationProvider(
    private val accountRepository: AccountRepository,
) : UserDetailsService {

    override fun loadUserByUsername(email: String) =
        accountRepository.findByEmail(email) ?: throw AccountNotFoundException(email)

    fun getAccountEmail() =
        (SecurityContextHolder.getContext().authentication.principal as? Account)?.email
            ?: ""
}