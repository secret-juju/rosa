package com.dsm.rosa.global.attribute

enum class Token(val millisecondOfExpirationTime: Long, val kind: String) {
    ACCESS(
        millisecondOfExpirationTime = 1000 * 60 * 60 * 24,
        kind = "accessToken",
    ),
    REFRESH(
        millisecondOfExpirationTime = 1000 * 60 * 60 * 24 * 7,
        kind = "refreshToken",
    ),
}