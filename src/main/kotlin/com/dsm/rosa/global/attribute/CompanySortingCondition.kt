package com.dsm.rosa.global.attribute

import com.dsm.rosa.domain.company.domain.QCompany
import com.dsm.rosa.domain.news.domain.QNews
import com.dsm.rosa.domain.stock.domain.QStock
import com.querydsl.core.types.dsl.ComparableExpressionBase

enum class CompanySortingCondition(
    val smallLetter: String,
    private val sortingCondition: ComparableExpressionBase<*>,
) {
    NAME(
        smallLetter = "name",
        sortingCondition = QCompany.company.name,
    ),
    POSITIVITY(
        smallLetter = "positivity",
        sortingCondition = QNews.news.positivity.avg(),
    ),
    CURRENT_PRICE(
        smallLetter = "current-price",
        sortingCondition = QStock.stock.closingPrice,
    ),
    DIFFERENCE_FROM_YESTERDAY(
        smallLetter = "difference-from-yesterday",
        sortingCondition = QStock.stock.differenceFromYesterday,
    ),
    FLUCTUATION_RATE(
        smallLetter = "fluctuation-rate",
        sortingCondition = QStock.stock.fluctuationRate,
    );

    fun apply(sortingMethod: CompanySortingMethod) =
        sortingMethod.toOrderSpecifier.invoke(this.sortingCondition)
}