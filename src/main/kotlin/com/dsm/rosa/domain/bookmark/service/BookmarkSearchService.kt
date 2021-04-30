package com.dsm.rosa.domain.bookmark.service

import com.dsm.rosa.domain.bookmark.repository.BookmarkRepository
import org.springframework.stereotype.Service

@Service
class BookmarkSearchService(
    private val bookmarkRepository: BookmarkRepository,
) {

    fun isExistBookmark(
        accountEmail: String,
        companyTickerSymbol: String,
    ) = bookmarkRepository.existsByAccountEmailAndCompanyTickerSymbol(
        accountEmail = accountEmail,
        companyTickerSymbol = companyTickerSymbol,
    )
}