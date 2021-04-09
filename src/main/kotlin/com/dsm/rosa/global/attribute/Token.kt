package com.dsm.rosa.global.attribute

enum class Token(val millisecondOfExpirationTime: Long) {
    ACCESS(1000 * 60 * 60 * 24),
    REFRESH(1000 * 60 * 60 * 24 * 7),
}