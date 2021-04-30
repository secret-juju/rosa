package com.dsm.rosa.global.exception

import org.springframework.http.HttpStatus

open class CommonException(
    val code: String,
    override val message: String,
    val status: HttpStatus,
) : RuntimeException()