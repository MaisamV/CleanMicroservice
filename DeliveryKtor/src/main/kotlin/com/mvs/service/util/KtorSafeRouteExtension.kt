package com.mvs.service.util

import com.mvs.exception.BaseException
import com.mvs.exception.handleError
import com.mvs.exception.toErrorData
import com.mvs.service.dto.BaseResponse
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

fun Route.addRoute(path: String, build: Route.() -> Unit) {
    if(path.endsWith('/')) {
        path.removeRange(path.length - 2, path.length - 1)
    }
    route(path, build)
    route("$path/", build)
}

inline fun Route.headSafe(
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit
): Route {
    return head( getSafeBody(body))
}

inline fun Route.getSafe(
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit
): Route {
    return get( getSafeBody(body))
}

inline fun Route.optionsSafe(
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit
): Route {
    return options(getSafeBody(body))
}

inline fun Route.postSafe(
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit
): Route {
    return post( getSafeBody(body))
}

inline fun Route.putSafe(
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit
): Route {
    return put( getSafeBody(body))
}

inline fun Route.patchSafe(
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit
): Route {
    return patch( getSafeBody(body))
}

inline fun Route.deleteSafe(
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit
): Route {
    return delete( getSafeBody(body))
}

suspend inline fun PipelineContext<Unit, ApplicationCall>.handleKtorErrors(body: () -> Unit) {
    handleError({ e -> respondError(e) }, body)
}

suspend inline fun PipelineContext<Unit, ApplicationCall>.respondError(exception: BaseException) {
    val statusCode = getHttpStatusCode(exception)
    call.respond(statusCode, BaseResponse(false, null, exception.toErrorData()))
}

inline fun getSafeBody(
    noinline body: suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit
): (suspend PipelineContext<Unit, ApplicationCall>.(Unit) -> Unit) {
    return {
        handleKtorErrors {
            body.invoke(this, it)
        }
    }
}