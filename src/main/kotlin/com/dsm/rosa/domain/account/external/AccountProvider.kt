package com.dsm.rosa.domain.account.external

interface AccountSearchService {
    fun searchAccount(authorization: String): String
}