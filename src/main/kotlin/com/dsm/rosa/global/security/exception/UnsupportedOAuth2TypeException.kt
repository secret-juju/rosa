package com.dsm.rosa.global.security.exception

import org.springframework.http.HttpStatus

class UnsupportedOAuth2TypeException(
    oauth2Type: String,
) : CommonException(
    errorCode = "UNSUPPORTED_OAUTH2_TYPE",
    errorMessage = "지원하지 않는 OAuth2 타입입니다. [$oauth2Type]",
    errorStatus = HttpStatus.BAD_REQUEST,
)