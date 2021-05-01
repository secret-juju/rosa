package com.dsm.rosa.domain.company.repository

import com.dsm.rosa.domain.company.domain.Company
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface CompanyRepository : JpaRepository<Company, Long> {
    @EntityGraph(attributePaths = ["stocks", "news"])
    @Query(value = "SELECT c FROM Company c INNER JOIN Stock s ON c.id = s.company.id and s.date = :date ORDER BY s.closingPrice")
    fun findByOrderByClosingPrice(
        pageable: Pageable,
        @Param("date") date: LocalDate,
    ): Page<Company>
}

//class CompanyRepository(
//    val query: JPAQueryFactory,
//) {
//
//    fun findByOrderBy() {
////        query.selectFrom(QCom)
//    }
//}