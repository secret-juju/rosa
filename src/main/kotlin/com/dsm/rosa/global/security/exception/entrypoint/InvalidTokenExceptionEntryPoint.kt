package com.dsm.rosa.global.security.exception.entrypoint

import com.dsm.rosa.global.security.exception.response.ExceptionResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class InvalidTokenExceptionEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException,
    ) {

        val objectMapper = jacksonObjectMapper().findAndRegisterModules()
        val exceptionResponse = objectMapper.writeValueAsString(
            ExceptionResponse(
                errorCode = "INVALID_TOKEN",
                errorMessage = "Invalid Token",
            )
        )

        response.contentType = "application/json;charset=UTF-8"
        response.characterEncoding = "UTF-8"
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.outputStream.println(exceptionResponse)
    }
}