package com.dsm.rosa.global.exception

import org.springframework.http.HttpStatus

class NonExistCompanySortingMethodException(
    companySortingMethod: String,
) : CommonException(
    code = "NON_EXIST_SORTING_METHOD",
    message = "존재하지 않는 회사 정렬 방법입니다. [$companySortingMethod]",
    status = HttpStatus.BAD_REQUEST,
)