package com.mvs.service.util

import com.mvs.exception.ErrorData
import com.mvs.service.dto.BaseResponse
import com.mvs.exception.BaseException
import com.mvs.exception.toErrorData
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend inline fun <reified T : Any> ApplicationCall.respondOk(data: T?) {
    respond(
        HttpStatusCode.OK,
        BaseResponse(data)
    )
}

suspend inline fun ApplicationCall.respondError(error: ErrorData) {
    respond(
        HttpStatusCode.InternalServerError,
        BaseResponse(null, listOf(error)))
}

suspend inline fun ApplicationCall.respondError(exception: BaseException) {
    respond(
        getHttpStatusCode(exception),
        BaseResponse(null, listOf(exception.toErrorData())))
}

suspend inline fun ApplicationCall.respondError(status: HttpStatusCode, error: ErrorData) {
    respond(
        status,
        BaseResponse(null, listOf(error)))
}