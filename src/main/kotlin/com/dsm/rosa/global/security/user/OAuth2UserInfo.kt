package com.dsm.rosa.global.security.user

abstract class OAuth2UserInfo(var attributes: Map<String, Any>) {
    abstract val id: String
    abstract val email: String
    abstract val name: String
}