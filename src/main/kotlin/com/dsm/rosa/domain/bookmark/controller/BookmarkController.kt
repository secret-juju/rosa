package com.dsm.rosa.domain.bookmark.controller

import com.dsm.rosa.domain.bookmark.service.BookmarkCreationService
import com.dsm.rosa.domain.bookmark.service.BookmarkDeletionService
import com.dsm.rosa.global.security.provider.AuthenticationProvider
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class BookmarkController(
    private val bookmarkCreationService: BookmarkCreationService,
    private val bookmarkDeletionService: BookmarkDeletionService,
    private val authenticationProvider: AuthenticationProvider,
) {

    @PostMapping("/company/{companyTickerSymbol}/bookmark")
    @ResponseStatus(HttpStatus.OK)
    fun subscribeCompany(
        @PathVariable("companyTickerSymbol")
        companyTickerSymbol: String,
    ) {
        bookmarkCreationService.applyBookmark(
            accountEmail = authenticationProvider.getAccountEmail(),
            companyTickerSymbol = companyTickerSymbol,
        )
    }

    @DeleteMapping("/company/{companyTickerSymbol}/bookmark")
    @ResponseStatus(HttpStatus.OK)
    fun unsubscribeCompany(
        @PathVariable("companyTickerSymbol")
        companyTickerSymbol: String,
    ) {
        bookmarkDeletionService.removeBookmark(
            accountEmail = authenticationProvider.getAccountEmail(),
            companyTickerSymbol = companyTickerSymbol,
        )
    }
}