package com.dsm.rosa.domain.bookmark.exception

import com.dsm.rosa.global.exception.CommonException
import org.springframework.http.HttpStatus

class AlreadyApplyBookmarkException(
    companyTickerSymbol: String,
) : CommonException(
    code = "ALREADY_APPLY_BOOKMARK",
    message = "이미 즐겨찾기 되어 있는 회사입니다. [종목코드=$companyTickerSymbol]",
    status = HttpStatus.BAD_REQUEST,
)