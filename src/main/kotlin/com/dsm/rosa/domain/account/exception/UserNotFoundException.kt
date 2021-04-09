package com.dsm.rosa.domain.account.exception

import com.dsm.rosa.global.security.exception.CommonException
import org.springframework.http.HttpStatus

class UserNotFoundException(
    userEmail: String,
) : CommonException(
    errorCode = "USER_NOT_FOUND",
    errorMessage = "유저를 찾을 수 없습니다. [$userEmail]",
    errorStatus = HttpStatus.NOT_FOUND,
)