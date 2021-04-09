package com.dsm.rosa.global.converter

import com.dsm.rosa.global.attribute.OAuth2Type
import com.dsm.rosa.global.security.exception.UnsupportedOAuth2TypeException
import org.springframework.core.convert.converter.Converter

class OAuthTypeConverter : Converter<String, OAuth2Type> {

    override fun convert(oauth2Type: String) =
        OAuth2Type.values().singleOrNull { it.name == oauth2Type }
            ?: throw UnsupportedOAuth2TypeException(oauth2Type)
}