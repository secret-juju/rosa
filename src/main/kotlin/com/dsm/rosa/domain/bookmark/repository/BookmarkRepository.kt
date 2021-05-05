package com.dsm.rosa.domain.bookmark.repository

import com.dsm.rosa.domain.bookmark.domain.Bookmark
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository : JpaRepository<Bookmark, Long> {
    @EntityGraph(attributePaths = ["company", "company.stocks", "company.news"])
    fun findByAccountEmail(accountEmail: String, pageable: Pageable): Slice<Bookmark>
    fun existsByAccountEmailAndCompanyTickerSymbol(accountEmail: String, companyTickerSymbol: String): Boolean
    fun deleteByAccountEmailAndCompanyTickerSymbol(accountEmail: String, companyTickerSymbol: String)
}