package com.dsm.rosa.global.converter

import com.dsm.rosa.global.attribute.CompanySortingMethod
import com.dsm.rosa.global.exception.NonExistCompanySortingMethodException
import org.springframework.core.convert.converter.Converter

class CompanySortingMethodConverter : Converter<String, CompanySortingMethod> {

    override fun convert(request: String) =
        CompanySortingMethod
            .values()
            .singleOrNull { request == it.method }
            ?: throw NonExistCompanySortingMethodException(request)
}