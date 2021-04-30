package com.dsm.rosa.domain.account.exception

import com.dsm.rosa.global.exception.CommonException
import org.springframework.http.HttpStatus

class AccountNotFoundException(
    accountEmail: String,
) : CommonException(
    code = "ACCOUNT_NOT_FOUND",
    message = "계정을 찾을 수 없습니다. [$accountEmail]",
    status = HttpStatus.NOT_FOUND,
)