package com.mvs.service.exception

import com.mvs.service.dto.ErrorData

fun <T: BaseException> T.toErrorData(): ErrorData = ErrorData(code, errorMessage, localMessage)