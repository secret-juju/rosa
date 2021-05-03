package com.dsm.rosa.domain.company.repository.dto

data class SortingCompanyDto(
    val companyId: Long,
    val companyTickerSymbol: String,
    val companyName: String,
    val stockCurrentPrice: Long,
    val stockDifferenceFromYesterday: Long,
    val stockFluctuationRate: Double,
    val averagePositivity: Double,
)