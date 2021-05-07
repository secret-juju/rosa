package com.dsm.rosa.domain.company.controller.response

data class IndustrySortingMultipleCompanyResponse(
    val companies: List<CompanyResponse>,
    val isLastPage: Boolean,
    val currentPageNumber: Long,
    val averageFluctuationRate: Double,
) {

    data class CompanyResponse(
        val name: String,
        val averagePositivity: Double,
        val currentPrice: Long,
        val differenceFromYesterday: Long,
        val fluctuationRate: Double,
    )
}