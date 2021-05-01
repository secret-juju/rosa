package com.dsm.rosa.global.exception

import org.springframework.http.HttpStatus

class NonExistCompanySortingColumnException(
    companySortingColumn: String,
) : CommonException(
    code = "NON_EXIST_SORTING_COLUMN",
    message = "존재하지 않는 회사 정렬 컬럼입니다. [$companySortingColumn]",
    status = HttpStatus.BAD_REQUEST,
)