package com.dsm.rosa.domain.bookmark.repository

import com.dsm.rosa.domain.bookmark.domain.Bookmark
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository : JpaRepository<Bookmark, Long> {
    fun existsByAccountEmailAndCompanyTickerSymbol(accountEmail: String, companyTickerSymbol: String): Boolean
    fun deleteByAccountEmailAndCompanyTickerSymbol(accountEmail: String, companyTickerSymbol: String)
}