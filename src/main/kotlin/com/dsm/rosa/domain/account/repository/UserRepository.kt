package com.dsm.rosa.domain.account.repository

import com.dsm.rosa.domain.account.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}