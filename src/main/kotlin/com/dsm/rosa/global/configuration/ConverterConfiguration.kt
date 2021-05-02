package com.dsm.rosa.global.configuration

import com.dsm.rosa.global.converter.CompanySortingColumnConverter
import com.dsm.rosa.global.converter.CompanySortingMethodConverter
import com.dsm.rosa.global.converter.OAuthTypeConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ConverterConfiguration : WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(OAuthTypeConverter())
        registry.addConverter(CompanySortingColumnConverter())
        registry.addConverter(CompanySortingMethodConverter())
    }
}