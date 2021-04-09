package com.dsm.rosa.global.security.exception

import org.springframework.http.HttpStatus

open class CommonException(
    val errorCode: String,
    private val errorMessage: String,
    val errorStatus: HttpStatus,
) : RuntimeException(errorMessage)