package com.dsm.rosa.domain.bookmark.service

import com.dsm.rosa.domain.bookmark.exception.BookmarkNotFoundException
import com.dsm.rosa.domain.bookmark.repository.BookmarkRepository
import org.springframework.stereotype.Service

@Service
class BookmarkDeletionService(
    private val bookmarkRepository: BookmarkRepository,
) {

    fun removeBookmark(accountEmail: String, companyTickerSymbol: String) =
        if (isAlreadyApplyBookmark(accountEmail, companyTickerSymbol))
            deleteBookmark(
                accountEmail = accountEmail,
                companyTickerSymbol = companyTickerSymbol,
            )
        else
            throw BookmarkNotFoundException(accountEmail, companyTickerSymbol)

    private fun isAlreadyApplyBookmark(accountEmail: String, companyTickerSymbol: String) =
        bookmarkRepository.existsByAccountEmailAndCompanyTickerSymbol(
            accountEmail = accountEmail,
            companyTickerSymbol = companyTickerSymbol,
        )

    private fun deleteBookmark(accountEmail: String, companyTickerSymbol: String) =
        bookmarkRepository.deleteByAccountEmailAndCompanyTickerSymbol(
            accountEmail = accountEmail,
            companyTickerSymbol = companyTickerSymbol,
        )
}