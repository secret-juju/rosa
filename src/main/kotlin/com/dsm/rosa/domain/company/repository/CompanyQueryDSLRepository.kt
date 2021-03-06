package com.dsm.rosa.domain.company.repository

import com.dsm.rosa.domain.company.domain.QCompany
import com.dsm.rosa.domain.news.domain.QNews
import com.dsm.rosa.domain.stock.domain.QStock
import com.dsm.rosa.global.attribute.CompanySortingCondition
import com.dsm.rosa.global.attribute.CompanySortingMethod
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class CompanyQueryDSLRepository(
    private val queryFactory: JPAQueryFactory,
) {

    fun findBySortingCondition(
        pageable: Pageable,
        sortingCondition: CompanySortingCondition,
        sortingMethod: CompanySortingMethod,
        date: LocalDate = LocalDate.now(),
    ) = toSlice(
        contents = queryFactory
            .selectFrom(QCompany.company)
            .leftJoin(QCompany.company.stocks, QStock.stock).fetchJoin()
            .leftJoin(QCompany.company.news, QNews.news).fetchJoin()
            .where(QStock.stock.date.eq(date))
            .orderBy(sortingCondition.apply(sortingMethod))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong() + 1)
            .fetch(),
        pageable = pageable,
    )

    fun findByOrderByAveragePositivity(
        pageable: Pageable,
        sortingCondition: CompanySortingCondition,
        sortingMethod: CompanySortingMethod,
        date: LocalDate = LocalDate.now(),
    ) = toSlice(
        contents = queryFactory
            .selectDistinct(QCompany.company)
            .from(QCompany.company)
            .leftJoin(QCompany.company.stocks, QStock.stock).fetchJoin()
            .leftJoin(QCompany.company.news, QNews.news).fetchJoin()
            .where(QStock.stock.date.eq(date))
            .fetch()
            .sortedBy {
                if (sortingMethod == CompanySortingMethod.ASCENDING) {
                    it.news.map { news -> news.positivity }.average()
                } else {
                    -it.news.map { news -> news.positivity }.average()
                }
            }
            .chunked(pageable.pageSize)
            .let {
                val searchResult = it[pageable.pageNumber].toMutableList()
                if (pageable.pageNumber + 1 < it.size) {
                    searchResult.add(it[pageable.pageNumber + 1][0])
                }
                return@let searchResult
            },
        pageable = pageable,
    )

    private fun <T> toSlice(contents: MutableList<T>, pageable: Pageable): Slice<T> {
        var hasNext = false

        if (contents.size > pageable.pageSize) {
            contents.removeAt(pageable.pageSize)
            hasNext = true
        }

        return SliceImpl(contents, pageable, hasNext)
    }
}