package com.dsm.rosa.domain.company.repository

import com.dsm.rosa.domain.company.domain.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, Long> {
    fun findByTickerSymbol(tickerSymbol: String): Company?
}