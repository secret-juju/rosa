package com.dsm.rosa.domain.industry.controller

import com.dsm.rosa.domain.industry.controller.response.KindOfIndustryResponse
import com.dsm.rosa.domain.industry.service.IndustrySearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndustryController(
    private val industrySearchService: IndustrySearchService,
) {

    @GetMapping("/industry")
    fun searchKindOfIndustry() =
        KindOfIndustryResponse(
            industryNames = industrySearchService.getIndustryName(),
        )
}