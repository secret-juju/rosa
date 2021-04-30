package com.dsm.rosa.global.exception.handler

import com.dsm.rosa.global.exception.CommonException
import com.dsm.rosa.global.exception.response.ExceptionResponse
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
                code = e.code,
                message = e.message,
            ),
            e.status,
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException) =
        ExceptionResponse(
            code = "INVALID_REQUEST",
            message = e.bindingResult.fieldError?.defaultMessage ?: "알 수 없는 에러",
        )

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException) =
        ExceptionResponse(
            code = "INVALID_JSON",
            message = "JSON 형식이 잘못되었습니다.",
        )
}