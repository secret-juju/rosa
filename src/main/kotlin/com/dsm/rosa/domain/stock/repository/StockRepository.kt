package com.dsm.rosa.domain.stock.repository

import com.dsm.rosa.domain.stock.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface StockRepository : JpaRepository<Stock, Long> {
    fun findByCompanyTickerSymbolAndDate(companyTickerSymbol: String, publishedDate: LocalDate): Stock?
}