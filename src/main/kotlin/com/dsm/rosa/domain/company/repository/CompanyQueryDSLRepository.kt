package com.dsm.rosa.domain.company.repository

import com.dsm.rosa.domain.company.domain.QCompany
import com.dsm.rosa.domain.news.domain.QNews
import com.dsm.rosa.domain.stock.domain.QStock
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class CompanyQueryDSLRepository(
    private val query: JPAQueryFactory,
) {

    fun findBySortingCondition(pageable: Pageable) {
        println(
            query.selectFrom(QCompany.company)
                .innerJoin(QCompany.company.stocks, QStock.stock).fetchJoin()
                .leftJoin(QCompany.company.news, QNews.news).fetchJoin()
                .where(QStock.stock.date.eq(LocalDate.now()))
                .orderBy(QStock.stock.closingPrice.asc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetch()
        )
    }
}