package com.mvs.service.util

import com.mvs.exception.ErrorData
import com.mvs.service.dto.BaseResponse
import com.mvs.exception.BaseException
import com.mvs.service.exception.toErrorData
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend inline fun <reified T : Any> ApplicationCall.respondOk(data: T?) {
    respond(
        HttpStatusCode.OK,
        BaseResponse(true, data, null)
    )
}

suspend inline fun ApplicationCall.respondError(error: ErrorData) {
    respond(
        HttpStatusCode.InternalServerError,
        BaseResponse(false, null, error)
    )
}

suspend inline fun ApplicationCall.respondError(exception: BaseException) {
    respond(
        getHttpStatusCode(exception),
        BaseResponse(false, null, exception.toErrorData())
    )
}

suspend inline fun ApplicationCall.respondError(status: HttpStatusCode, error: ErrorData) {
    respond(
        status,
        BaseResponse(false, null, error)
    )
}