package com.dsm.rosa.domain.company.controller

import com.dsm.rosa.domain.company.service.CompanySearchService
import com.dsm.rosa.global.attribute.CompanySortingCondition
import com.dsm.rosa.global.attribute.CompanySortingMethod
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/company")
class CompanyController(
    private val companySearchService: CompanySearchService,
) {

    @GetMapping
    fun searchCompany(
        @RequestParam("sorting-condition")
        sortingCondition: CompanySortingCondition,
        @RequestParam("sorting-method")
        sortingMethod: CompanySortingMethod,
        pageInformation: Pageable,
    ) = companySearchService.searchCompany(
        pageInformation = pageInformation,
        sortingCondition = sortingCondition,
        sortingMethod = sortingMethod,
    )

    @GetMapping("/{companyIndustryName}")
    fun searchCompanyByCompanyIndustryName(
        @PathVariable("companyIndustryName")
        companyIndustryName: String,
        pageInformation: Pageable,
    ) = companySearchService.searchCompanyByIndustry(
        companyIndustryName = companyIndustryName,
        pageInformation = pageInformation,
    )
}