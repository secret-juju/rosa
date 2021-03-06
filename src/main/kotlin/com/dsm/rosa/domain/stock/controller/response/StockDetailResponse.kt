package com.dsm.rosa.domain.stock.controller.response

data class StockDetailResponse(
    val stock: StockResponse,
    val news: List<NewsResponse>,
    val isBookmarked: Boolean,
    val averagePositivity: Double,
    val companyName: String,
) {

    data class StockResponse(
        val closingPrice: Long,
        val yesterdayClosingPrice: Long,
        val differenceFromYesterday: Long,
        val fluctuationRate: Double,
        val openingPrice: Long,
        val highPrice: Long,
        val lowPrice: Long,
        val marketCapitalization: Long,
    )

    data class NewsResponse(
        val content: String,
        val positivity: Long,
    )
}