package com.dsm.rosa.domain.industry.repository

import com.dsm.rosa.domain.industry.domain.CompanyIndustryAffiliation
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyIndustryAffiliationRepository : JpaRepository<CompanyIndustryAffiliation, Long> {
    fun findByIndustryName(companyIndustryName: String, pageable: Pageable): Slice<CompanyIndustryAffiliation>
}