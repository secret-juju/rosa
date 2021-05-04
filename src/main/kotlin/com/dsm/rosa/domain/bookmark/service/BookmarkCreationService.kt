package com.dsm.rosa.domain.bookmark.service

import com.dsm.rosa.domain.account.exception.AccountNotFoundException
import com.dsm.rosa.domain.account.repository.AccountRepository
import com.dsm.rosa.domain.bookmark.domain.Bookmark
import com.dsm.rosa.domain.bookmark.exception.AlreadyApplyBookmarkException
import com.dsm.rosa.domain.bookmark.repository.BookmarkRepository
import com.dsm.rosa.domain.company.exception.CompanyNotFoundException
import com.dsm.rosa.domain.company.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class BookmarkCreationService(
    private val bookmarkRepository: BookmarkRepository,
    private val accountRepository: AccountRepository,
    private val companyRepository: CompanyRepository,
) {

    fun applyBookmark(accountEmail: String, companyTickerSymbol: String) =
        if (isAlreadyApplyBookmark(accountEmail, companyTickerSymbol))
            throw AlreadyApplyBookmarkException(companyTickerSymbol)
        else
            saveBookmark(
                accountEmail = accountEmail,
                companyTickerSymbol = companyTickerSymbol,
            )

    private fun isAlreadyApplyBookmark(accountEmail: String, companyTickerSymbol: String) =
        bookmarkRepository.existsByAccountEmailAndCompanyTickerSymbol(
            accountEmail = accountEmail,
            companyTickerSymbol = companyTickerSymbol,
        )

    private fun saveBookmark(accountEmail: String, companyTickerSymbol: String) {
        bookmarkRepository.save(
            Bookmark(
                account = createAccount(accountEmail),
                company = createCompany(companyTickerSymbol),
            )
        )
    }

    private fun createAccount(accountEmail: String) =
        accountRepository.findByEmail(accountEmail)
            ?: throw AccountNotFoundException(accountEmail)

    private fun createCompany(companyTickerSymbol: String) =
        companyRepository.findByTickerSymbol(companyTickerSymbol)
            ?: throw CompanyNotFoundException(companyTickerSymbol)
}