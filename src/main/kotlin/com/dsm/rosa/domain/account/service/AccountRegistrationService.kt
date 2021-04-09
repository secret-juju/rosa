package com.dsm.rosa.domain.account.service

import com.dsm.rosa.domain.account.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountCreationService(
    val accountRepository: AccountRepository,
) {

    fun createAccount()
}