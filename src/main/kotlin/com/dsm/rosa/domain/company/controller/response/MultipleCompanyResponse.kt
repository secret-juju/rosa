package com.dsm.rosa.domain.company.controller.response

data class MultipleCompanyResponse(
    val companies: List<CompanyResponse>,
) {

    data class CompanyResponse(
        val name: String,
        val averagePositivity: Double,
        val currentPrice: Long,
        val differenceFromYesterday: Long,
        val fluctuationRate: Double,
    )
}