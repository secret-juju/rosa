package com.dsm.rosa.domain.company.service

import com.dsm.rosa.domain.company.controller.response.MultipleCompanyResponse
import com.dsm.rosa.domain.company.domain.Company
import com.dsm.rosa.domain.company.repository.CompanyQueryDSLRepository
import com.dsm.rosa.domain.stock.domain.Stock
import com.dsm.rosa.global.attribute.CompanySortingCondition
import com.dsm.rosa.global.attribute.CompanySortingMethod
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.math.roundToLong

@Service
class CompanySearchService(
    private val companyQueryDSLRepository: CompanyQueryDSLRepository,
) {

    fun searchCompany(
        pageInformation: Pageable,
        sortingCondition: CompanySortingCondition,
        sortingMethod: CompanySortingMethod,
    ): MultipleCompanyResponse {
        val companies = getCompany(
            pageInformation = pageInformation,
            sortingCondition = sortingCondition,
            sortingMethod = sortingMethod,
        )

        return MultipleCompanyResponse(
            companies = companies
                .content
                .map {
                    val todayStock = getTodayStock(it)
                    val averagePositivity = calculateAveragePositivity(it)

                    MultipleCompanyResponse.CompanyResponse(
                        name = it.name,
                        averagePositivity = averagePositivity,
                        currentPrice = todayStock.closingPrice,
                        differenceFromYesterday = todayStock.differenceFromYesterday,
                        fluctuationRate = todayStock.fluctuationRate,
                    )
                },
            isLastPage = companies.isLast,
            currentPageNumber = companies.pageable.pageNumber.toLong() + 1,
        )
    }

    private fun getCompany(
        pageInformation: Pageable,
        sortingCondition: CompanySortingCondition,
        sortingMethod: CompanySortingMethod,
    ) = if (sortingCondition == CompanySortingCondition.POSITIVITY) {
        companyQueryDSLRepository.findByOrderByAveragePositivity(
            pageable = createPageRequest(
                pageInformation = pageInformation,
            ),
            sortingCondition = sortingCondition,
            sortingMethod = sortingMethod,
        )
    } else {
        companyQueryDSLRepository.findBySortingCondition(
            pageable = createPageRequest(
                pageInformation = pageInformation,
            ),
            sortingCondition = sortingCondition,
            sortingMethod = sortingMethod,
        )
    }

    private fun createPageRequest(
        pageInformation: Pageable,
    ) = PageRequest.of(
        pageInformation.pageNumber - 1,
        pageInformation.pageSize,
    )

    private fun getTodayStock(targetCompany: Company) =
        targetCompany
            .stocks
            .singleOrNull { stock -> stock.date == LocalDate.now() }
            ?: createDefaultStock(targetCompany)

    private fun createDefaultStock(defaultCompany: Company) =
        Stock(
            date = LocalDate.now(),
            closingPrice = -1,
            differenceFromYesterday = -1,
            fluctuationRate = -1.0,
            openingPrice = -1,
            highPrice = -1,
            lowPrice = -1,
            marketCapitalization = -1,
            company = defaultCompany,
        )

    private fun calculateAveragePositivity(targetCompany: Company): Double {
        val averagePositivity =
            targetCompany
                .news
                .map { news -> news.positivity }
                .average() * 100

        return if (!averagePositivity.isNaN())
            averagePositivity.roundToLong() / 100.0
        else -1.0
    }


}