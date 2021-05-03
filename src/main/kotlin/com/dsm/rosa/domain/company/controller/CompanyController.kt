package com.dsm.rosa.domain.company.controller

import com.dsm.rosa.domain.company.controller.response.MultipleCompanyResponse
import com.dsm.rosa.domain.company.repository.CompanyQueryDSLRepository
import com.dsm.rosa.domain.company.service.CompanySearchService
import com.dsm.rosa.domain.stock.exception.StockNotFoundException
import com.dsm.rosa.global.attribute.CompanySortingCondition
import com.dsm.rosa.global.attribute.CompanySortingMethod
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class CompanyController(
    private val companySearchService: CompanySearchService,
) {

    @GetMapping("/company")
    fun searchCompany(
        @RequestParam("sortingCondition")
        sortingCondition: CompanySortingCondition,
        @RequestParam("sorting-method")
        sortingMethod: CompanySortingMethod,
        pageInformation: Pageable,
    ) = MultipleCompanyResponse(
        companies = companySearchService.searchCompany(
            pageInformation = pageInformation,
            sortingCondition = sortingCondition,
            sortingMethod = sortingMethod,
        )
    )
}