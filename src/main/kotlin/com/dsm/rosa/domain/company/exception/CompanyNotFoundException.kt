package com.dsm.rosa.domain.company.exception

import com.dsm.rosa.global.exception.CommonException
import org.springframework.http.HttpStatus

class CompanyNotFoundException(
    companyTickerSymbol: String,
) : CommonException(
    code = "COMPANY_NOT_FOUND",
    message = "종목코드가 ${companyTickerSymbol}인 회사를 찾을 수 없습니다.",
    status = HttpStatus.NOT_FOUND,
)