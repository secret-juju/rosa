package com.dsm.rosa.domain.industry.repository

import com.dsm.rosa.domain.industry.domain.Industry
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface IndustryRepository : JpaRepository<Industry, Long> {
    @EntityGraph(attributePaths = ["company", "company.stocks", "company.news"])
    fun findByName(companyIndustryName: String, pageable: Pageable): Slice<Industry>
}