package com.dsm.rosa.global.security.exception

import org.springframework.http.HttpStatus

class InvalidTokenException : CommonException(
    errorCode = "INVALID_TOKEN",
    errorMessage = "토큰이 잘못되었습니다.",
    errorStatus = HttpStatus.UNAUTHORIZED,
)