package com.dsm.rosa.global.attribute

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.ComparableExpressionBase

enum class CompanySortingMethod(
    val method: String,
    val toOrderSpecifier: (ComparableExpressionBase<*>) -> (OrderSpecifier<*>)) {
    ASCENDING(
        method = "asc",
        toOrderSpecifier = { it.asc() }
    ),
    DESCENDING(
        method = "desc",
        toOrderSpecifier = { it.desc() }
    );
}