package com.dsm.rosa.global.converter

import com.dsm.rosa.global.attribute.CompanySortingColumn
import com.dsm.rosa.global.exception.NonExistCompanySortingColumnException
import org.springframework.core.convert.converter.Converter

class CompanySortingColumnConverter : Converter<String, CompanySortingColumn> {

    override fun convert(request: String) =
        CompanySortingColumn
            .values()
            .singleOrNull { request == it.smallLetter }
            ?: throw NonExistCompanySortingColumnException(request)
}