package com.mvs.service.util

import com.mvs.service.dto.ErrorData
import com.mvs.service.dto.BaseResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend inline fun <reified T : Any> ApplicationCall.respondOk(data: T) {
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