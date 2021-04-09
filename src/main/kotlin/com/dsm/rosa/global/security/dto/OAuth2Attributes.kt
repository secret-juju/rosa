package com.dsm.rosa.global.security.dto

import com.dsm.rosa.domain.account.domain.User

class OAuth2Attributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val email: String,
) {

    companion object {

        fun of(
            registrationId: String,
            nameAttributeKey: String,
            attributes: Map<String, Any>
        ) = ofGoogle(nameAttributeKey, attributes)

        private fun ofGoogle(
            nameAttributeKey: String,
            attributes: Map<String, Any>
        ) = OAuth2Attributes(
            attributes = attributes,
            nameAttributeKey = nameAttributeKey,
            email = attributes["email"] as String,
        )
    }

    fun toEntity() = User(email = email)
}