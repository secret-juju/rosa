package com.dsm.rosa.domain.industry.repository

import com.dsm.rosa.domain.industry.domain.CompanyIndustryAffiliation
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyIndustryAffiliationRepository : JpaRepository<CompanyIndustryAffiliation, Long> {
    @EntityGraph(attributePaths = ["company", "company.stocks", "company.news"])
    fun findByIndustryName(companyIndustryName: String, pageable: Pageable): Slice<CompanyIndustryAffiliation>
}