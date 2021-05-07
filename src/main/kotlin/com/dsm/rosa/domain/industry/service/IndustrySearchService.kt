package com.dsm.rosa.domain.industry.service

import com.dsm.rosa.domain.industry.domain.Industry
import com.dsm.rosa.domain.industry.repository.IndustryRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class IndustrySearchService(
    private val industryRepository: IndustryRepository,
) {

    fun getIndustryName() =
        getKindOfIndustry()
            .map { it.name }
            .distinctBy { it }

    private fun getKindOfIndustry(): List<Industry> =
        industryRepository.findAll(Sort.by("name"))
}