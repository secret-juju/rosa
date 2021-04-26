package com.dsm.rosa.global.attribute

enum class OAuth2Type(val value: String, val baseUrl: String) {
    GOOGLE("google", "https://www.googleapis.com"),
    FACEBOOK("facebook", "https://graph.facebook.com"),
    NAVER("naver", "https://openapi.naver.com"),
}