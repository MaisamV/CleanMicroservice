package com.mvs.service.exception

import com.mvs.service.dto.ErrorData

abstract class RemoteException(val code: Long, val errorMessage: String, val localMessage: String): RuntimeException() {
    fun toErrorData() = ErrorData(code, errorMessage, localMessage)
}