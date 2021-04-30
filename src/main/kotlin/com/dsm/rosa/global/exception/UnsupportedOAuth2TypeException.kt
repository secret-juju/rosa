package com.dsm.rosa.global.exception

import org.springframework.http.HttpStatus

class UnsupportedOAuth2TypeException(
    oauth2Type: String,
) : CommonException(
    code = "UNSUPPORTED_OAUTH2_TYPE",
    message = "지원하지 않는 OAuth2 타입입니다. [$oauth2Type]",
    status = HttpStatus.BAD_REQUEST,
)