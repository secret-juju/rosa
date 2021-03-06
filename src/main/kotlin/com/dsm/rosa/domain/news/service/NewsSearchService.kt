package com.dsm.rosa.domain.news.service

import com.dsm.rosa.domain.news.repository.NewsRepository
import com.dsm.rosa.domain.stock.controller.response.StockDetailResponse
import org.springframework.stereotype.Service

@Service
class NewsSearchService(
    private val newsRepository: NewsRepository,
) {

    fun getNewsByCompany(
        companyTickerSymbol: String,
    ) = newsRepository.findByCompanyTickerSymbol(
        companyTickerSymbol = companyTickerSymbol,
    ).map {
        StockDetailResponse.NewsResponse(
            content = it.content,
            positivity = it.positivity,
        )
    }

    fun getAveragePositivity(
        companyTickerSymbol: String,
    ): Double {
        val averagePositivity = newsRepository.findByCompanyTickerSymbol(
            companyTickerSymbol = companyTickerSymbol,
        ).map { it.positivity }.average()

        return if (averagePositivity.isNaN()) 0.0 else averagePositivity
    }
}