package com.dsm.rosa.domain.bookmark.exception

import com.dsm.rosa.global.exception.CommonException
import org.springframework.http.HttpStatus

class BookmarkNotFoundException(
    accountEmail: String,
    companyTickerSymbol: String,
) : CommonException(
    code = "BOOKMARK_NOT_FOUND",
    message = "$accountEmail 유저는 종목코드가 ${companyTickerSymbol}인 회사를 즐겨찾기 하지 않았습니다.",
    status = HttpStatus.NOT_FOUND,
)