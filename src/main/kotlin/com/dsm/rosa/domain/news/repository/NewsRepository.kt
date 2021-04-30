package com.dsm.rosa.domain.news.repository

import com.dsm.rosa.domain.news.domain.News
import org.springframework.data.jpa.repository.JpaRepository

interface NewsRepository : JpaRepository<News, Long> {
    fun findByCompanyTickerSymbol(companyTickerSymbol: String): List<News>
}