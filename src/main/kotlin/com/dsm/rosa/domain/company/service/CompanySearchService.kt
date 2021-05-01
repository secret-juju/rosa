package com.dsm.rosa.domain.company.service

import com.dsm.rosa.domain.company.controller.response.MultipleCompanyResponse
import com.dsm.rosa.domain.company.repository.CompanyRepository
import com.dsm.rosa.domain.news.service.NewsSearchService
import com.dsm.rosa.domain.stock.exception.StockNotFoundException
import com.dsm.rosa.domain.stock.service.StockSearchService
import com.dsm.rosa.global.attribute.CompanySortingColumn
import com.dsm.rosa.global.attribute.CompanySortingMethod
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CompanySearchService(
    private val companyRepository: CompanyRepository,
) {

    fun searchCompany(
        pageInformation: Pageable,
        sortingColumn: CompanySortingColumn,
        sortingMethod: CompanySortingMethod,
    ) = getCompany(
            pageInformation = pageInformation,
            sortingColumn = sortingColumn,
            sortingMethod = sortingMethod,
        ).content
            .map {
                val todayStock = it
                    .stocks
                    .singleOrNull { stock -> stock.date == LocalDate.now() }
                    ?: throw StockNotFoundException(it.tickerSymbol)

                val averagePositivity = it
                    .news
                    .map { news -> news.positivity }
                    .average()

                MultipleCompanyResponse.CompanyResponse(
                    name = it.name,
                    averagePositivity = averagePositivity,
                    currentPrice = todayStock.closingPrice,
                    differenceFromYesterday = todayStock.differenceFromYesterday,
                    fluctuationRate = todayStock.fluctuationRate,
                )
            }

    private fun getCompany(
        pageInformation: Pageable,
        sortingColumn: CompanySortingColumn,
        sortingMethod: CompanySortingMethod,
    ) = companyRepository.findByOrderByClosingPrice(
        pageable = createPageRequest(
            pageInformation = pageInformation,
            sortingColumn = sortingColumn,
            sortingMethod = sortingMethod,
        ),
        date = LocalDate.now(),
    )

    private fun createPageRequest(
        pageInformation: Pageable,
        sortingColumn: CompanySortingColumn,
        sortingMethod: CompanySortingMethod,
    ) = PageRequest.of(
        pageInformation.pageNumber,
        pageInformation.pageSize,
//        when (sortingMethod) {
//            CompanySortingMethod.ASCENDING -> Sort.by(sortingColumn.sortingName).ascending()
//            CompanySortingMethod.DESCENDING -> Sort.by(sortingColumn.sortingName).descending()
//        }
    )
}