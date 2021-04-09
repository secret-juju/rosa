package com.dsm.rosa.global.security.exception.handler

import com.dsm.rosa.global.security.exception.CommonException
import com.dsm.rosa.global.security.exception.response.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(CommonException::class)
    fun commonExceptionHandler(e: CommonException) =
        ResponseEntity(
            ExceptionResponse(
                errorCode = e.errorCode,
                errorMessage = e.message?: "알 수 없는 오류",
            ),
            e.errorStatus,
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException) =
        ExceptionResponse(
            errorCode = "INVALID_REQUEST",
            errorMessage = e.bindingResult.fieldError?.defaultMessage ?: "알 수 없는 에러",
        )

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException) =
        ExceptionResponse(
            errorCode = "INVALID_JSON",
            errorMessage = "JSON 형식이 잘못되었습니다.",
        )
}