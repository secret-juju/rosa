package com.dsm.rosa.global.security.provider

enum class Token(val millisecondOfExpirationTime: Long) {
    ACCESS(1000 * 60 * 60 * 24),
    REFRESH(1000 * 60 * 60 * 24 * 7),
}