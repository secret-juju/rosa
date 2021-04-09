package com.dsm.rosa.domain.account.exception

import com.dsm.rosa.global.security.exception.CommonException
import org.springframework.http.HttpStatus

class AccountNotFoundException(
    accountEmail: String,
) : CommonException(
    errorCode = "ACCOUNT_NOT_FOUND",
    errorMessage = "계정을 찾을 수 없습니다. [$accountEmail]",
    errorStatus = HttpStatus.NOT_FOUND,
)