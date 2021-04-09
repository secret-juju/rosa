package com.dsm.rosa.global.security.user

class GoogleOAuth2UserInfo(attributes: Map<String, Any>) : OAuth2UserInfo(attributes) {
    override val id: String
        get() = attributes["id"] as String
    override val email: String
        get() = attributes["email"] as String
    override val name: String
        get() = attributes["name"] as String
}