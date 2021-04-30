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
            publishedDate = it.publishedDate,
        )
    }

    fun getAveragePositivity(
        companyTickerSymbol: String,
    ) = newsRepository.findByCompanyTickerSymbol(
        companyTickerSymbol = companyTickerSymbol,
    ).map { it.positivity }.average()
}