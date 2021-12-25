package com.mvs.service.exception

import com.mvs.service.dto.ErrorData

fun <T: RemoteException> T.toErrorData(): ErrorData = ErrorData(code, errorMessage, localMessage)