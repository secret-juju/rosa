package com.dsm.rosa.global.attribute

enum class CompanySortingRule(val smallLetter: String, val sortingName: String) {
    INDUSTRY(
        smallLetter = "industry",
        sortingName = "industry"
    ),
    NAME(
        smallLetter = "name",
        sortingName = "name"
    ),
    POSITIVITY(
        smallLetter = "positivity",
        sortingName = "positivity"
    ),
    CLOSING_PRICE(
        smallLetter = "closing-price",
        sortingName = "closing_price"
    ),
    DIFFERENCE_FROM_YESTERDAY(
        smallLetter = "difference-from-yesterday",
        sortingName = "difference_from_yesterday"
    ),
    FLUCTUATION_RATE(
        smallLetter = "fluctuation-rate",
        sortingName = "fluctuation_rate"
    ),
}