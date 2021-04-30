package com.dsm.rosa.domain.stock.exception

import com.dsm.rosa.global.exception.CommonException
import org.springframework.http.HttpStatus

class StockNotFoundException(
    companyTickerSymbol: String,
) : CommonException(
    code = "STOCK_NOT_FOUND",
    message = "종목코드가 ${companyTickerSymbol}인 주식을 찾을 수 없습니다.",
    status = HttpStatus.NOT_FOUND,
)