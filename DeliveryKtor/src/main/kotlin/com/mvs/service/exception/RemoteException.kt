package com.mvs.service.exception

import com.mvs.service.dto.ErrorData
import io.ktor.http.*

abstract class RemoteException(val httpErrorCode: HttpStatusCode, val code: Long, val errorMessage: String, val localMessage: String): RuntimeException() {
    fun toErrorData() = ErrorData(code, errorMessage, localMessage)
}