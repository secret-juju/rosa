package com.dsm.rosa.domain.account.repository

import com.dsm.rosa.domain.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByEmail(email: String): Account?
}