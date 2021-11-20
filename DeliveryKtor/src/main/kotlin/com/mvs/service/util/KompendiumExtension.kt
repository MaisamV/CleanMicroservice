package com.mvs.service.util

import com.mvs.service.exception.UnknownException
import io.bkbn.kompendium.Notarized.notarizedDelete
import io.bkbn.kompendium.Notarized.notarizedGet
import io.bkbn.kompendium.Notarized.notarizedPost
import io.bkbn.kompendium.Notarized.notarizedPut
import io.bkbn.kompendium.models.meta.MethodInfo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

inline fun <reified TParam : Any, reified TResp : Any> Route.xGet(
    info: MethodInfo.GetInfo<TParam, TResp>,
    noinline body: PipelineInterceptor<Unit, ApplicationCall>
): Route {
    val newBody: PipelineInterceptor<Unit, ApplicationCall> = {
        try {
            body.invoke(this, it)
        } catch (e: Exception) {
            e.printStackTrace()
            call.respondError(HttpStatusCode.InternalServerError,
                UnknownException().toErrorData()
            )
        }
    }
    return notarizedGet(info, newBody)
}

inline fun <reified TParam : Any, reified TReq : Any, reified TResp : Any> Route.xPost(
    info: MethodInfo.PostInfo<TParam, TReq, TResp>,
    noinline body: PipelineInterceptor<Unit, ApplicationCall>
): Route {
    val newBody: PipelineInterceptor<Unit, ApplicationCall> = {
        try {
            body.invoke(this, it)
        } catch (e: Exception) {
            e.printStackTrace()
            call.respondError(HttpStatusCode.InternalServerError,
                UnknownException().toErrorData()
            )
        }
    }
    return notarizedPost(info, newBody)
}

inline fun <reified TParam : Any, reified TReq : Any, reified TResp : Any> Route.xPut(
    info: MethodInfo.PutInfo<TParam, TReq, TResp>,
    noinline body: PipelineInterceptor<Unit, ApplicationCall>,
): Route {
    val newBody: PipelineInterceptor<Unit, ApplicationCall> = {
        try {
            body.invoke(this, it)
        } catch (e: Exception) {
            e.printStackTrace()
            call.respondError(HttpStatusCode.InternalServerError,
                UnknownException().toErrorData()
            )
        }
    }
    return notarizedPut(info, newBody)
}

inline fun <reified TParam : Any, reified TResp : Any> Route.xDelete(
    info: MethodInfo.DeleteInfo<TParam, TResp>,
    noinline body: PipelineInterceptor<Unit, ApplicationCall>
): Route {
    val newBody: PipelineInterceptor<Unit, ApplicationCall> = {
        try {
            body.invoke(this, it)
        } catch (e: Exception) {
            e.printStackTrace()
            call.respondError(HttpStatusCode.InternalServerError,
                UnknownException().toErrorData()
            )
        }
    }
    return notarizedDelete(info, newBody)
}