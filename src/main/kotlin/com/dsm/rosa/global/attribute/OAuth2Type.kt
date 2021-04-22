package com.dsm.rosa.global.attribute

enum class OAuth2Type(val value: String, val baseUrl: String) {
    GOOGLE("google", "https://www.googleapis.com"),
    FACEBOOK("facebook", ""),
    NAVER("naver", "https://openapi.naver.com"),
}