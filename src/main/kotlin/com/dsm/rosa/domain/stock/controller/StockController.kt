package com.dsm.rosa.domain.stock.controller

import com.dsm.rosa.domain.bookmark.service.BookmarkSearchService
import com.dsm.rosa.domain.news.service.NewsSearchService
import com.dsm.rosa.domain.stock.controller.response.StockDetailResponse
import com.dsm.rosa.domain.stock.service.StockSearchService
import com.dsm.rosa.global.security.provider.AuthenticationProvider
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@Validated
class StockController(
    private val stockSearchService: StockSearchService,
    private val newsSearchService: NewsSearchService,
    private val bookmarkSearchService: BookmarkSearchService,
    private val authenticationProvider: AuthenticationProvider,
) {

    @GetMapping("/company/{companyTickerSymbol}/stock")
    fun searchStockDetail(
        @Size(min = 6, max = 6)
        @NotBlank
        @PathVariable("companyTickerSymbol")
        companyTickerSymbol: String,
    ) = StockDetailResponse(
        stock = stockSearchService.getStockDetailByCompany(
            companyTickerSymbol = companyTickerSymbol,
        ),
        news = newsSearchService.getNewsByCompany(
            companyTickerSymbol = companyTickerSymbol,
        ),
        isBookmarked = bookmarkSearchService.isExistBookmark(
            accountEmail = authenticationProvider.getAccountEmail(),
            companyTickerSymbol = companyTickerSymbol,
        ),
        averagePositivity = newsSearchService.getAveragePositivity(
            companyTickerSymbol = companyTickerSymbol,
        )
    )
}