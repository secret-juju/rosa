package com.dsm.rosa.global.attribute

import com.dsm.rosa.domain.company.domain.QCompany
import com.dsm.rosa.domain.news.domain.QNews
import com.dsm.rosa.domain.stock.domain.QStock
import com.querydsl.core.types.dsl.ComparableExpressionBase
import com.querydsl.core.types.dsl.NumberExpression
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath
import kotlin.reflect.KClass

enum class CompanySortingCondition(
    val smallLetter: String,
    private val sortingConditionType: KClass<*>,
    private val sortingCondition: ComparableExpressionBase<*>,
) {
    NAME(
        smallLetter = "name",
        sortingConditionType = StringPath::class,
        sortingCondition = QCompany.company.name,
    ),
    POSITIVITY(
        smallLetter = "positivity",
        sortingConditionType = NumberExpression::class,
        sortingCondition = QNews.news.positivity.avg(),
    ),
    CURRENT_PRICE(
        smallLetter = "current-price",
        sortingConditionType = NumberPath::class,
        sortingCondition = QStock.stock.closingPrice,
    ),
    DIFFERENCE_FROM_YESTERDAY(
        smallLetter = "difference-from-yesterday",
        sortingConditionType = NumberPath::class,
        sortingCondition = QStock.stock.differenceFromYesterday,
    ),
    FLUCTUATION_RATE(
        smallLetter = "fluctuation-rate",
        sortingConditionType = NumberPath::class,
        sortingCondition = QStock.stock.fluctuationRate,
    );

    fun apply(sortingMethod: CompanySortingMethod) =
        sortingMethod.toOrderSpecifier.invoke(this.sortingCondition)
}