package com.dsm.rosa.global.converter

import com.dsm.rosa.global.attribute.CompanySortingCondition
import com.dsm.rosa.global.exception.NonExistCompanySortingColumnException
import org.springframework.core.convert.converter.Converter

class CompanySortingColumnConverter : Converter<String, CompanySortingCondition> {

    override fun convert(request: String) =
        CompanySortingCondition
            .values()
            .singleOrNull { request == it.smallLetter }
            ?: throw NonExistCompanySortingColumnException(request)
}