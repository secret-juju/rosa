package com.dsm.rosa.domain.account.exception

import com.dsm.rosa.global.security.exception.CommonException
import org.springframework.http.HttpStatus

class OAuth2AuthenticationProcessingException(
    registrationId: String,
) : CommonException(
    errorCode = "OAUTH2_AUTHENTICATION_PROCESSING",
    errorMessage = "지원하지 않는 OAuth2. [$registrationId]",
    errorStatus = HttpStatus.BAD_REQUEST,
)