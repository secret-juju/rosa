package com.dsm.rosa.domain.stock.service

import com.dsm.rosa.domain.news.repository.NewsRepository
import com.dsm.rosa.domain.stock.controller.response.StockDetailResponse
import com.dsm.rosa.domain.stock.exception.StockNotFoundException
import com.dsm.rosa.domain.stock.repository.StockRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StockSearchService(
    private val stockRepository: StockRepository,
) {

    fun getStockDetailByCompany(
        companyTickerSymbol: String,
        publishedDate: LocalDate = LocalDate.now()
    ) = stockRepository.findByCompanyTickerSymbolAndDate(
        companyTickerSymbol = companyTickerSymbol,
        publishedDate = publishedDate,
    ).let {
        if (it == null)
            throw StockNotFoundException(companyTickerSymbol)

        StockDetailResponse.StockResponse(
            closingPrice = it.closingPrice,
            yesterdayClosingPrice = getClosingPrice(companyTickerSymbol, publishedDate) ?: 0,
            differenceFromYesterday = it.differenceFromYesterday,
            fluctuationRate = it.fluctuationRate,
            openingPrice = it.openingPrice,
            highPrice = it.highPrice,
            lowPrice = it.lowPrice,
            marketCapitalization = it.marketCapitalization,
        )
    }

    private fun getClosingPrice(
        companyTickerSymbol: String,
        publishedDate: LocalDate = LocalDate.now().minusDays(1),
    ) = stockRepository.findByCompanyTickerSymbolAndDate(
        companyTickerSymbol = companyTickerSymbol,
        publishedDate = publishedDate,
    )?.closingPrice
}