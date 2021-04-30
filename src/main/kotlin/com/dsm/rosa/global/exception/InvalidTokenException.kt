package com.dsm.rosa.global.exception

import org.springframework.http.HttpStatus

class InvalidTokenException : CommonException(
    code = "INVALID_TOKEN",
    message = "토큰이 잘못되었습니다.",
    status = HttpStatus.UNAUTHORIZED,
)