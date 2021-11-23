package com.mvs.service.util

import com.mvs.service.dto.BaseResponse
import com.mvs.service.dto.ErrorData
import com.mvs.service.exception.RemoteException
import com.mvs.service.exception.UnknownException
import com.papsign.ktor.openapigen.annotations.Response
import com.papsign.ktor.openapigen.getKType
import com.papsign.ktor.openapigen.model.operation.OperationModel
import com.papsign.ktor.openapigen.modules.RouteOpenAPIModule
import com.papsign.ktor.openapigen.modules.ofType
import com.papsign.ktor.openapigen.modules.providers.StatusProvider
import com.papsign.ktor.openapigen.modules.registerModule
import com.papsign.ktor.openapigen.openAPIGen
import com.papsign.ktor.openapigen.route.OpenAPIRoute
import com.papsign.ktor.openapigen.route.ThrowsInfo
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.OpenAPIPipelineResponseContext
import com.papsign.ktor.openapigen.route.route
import io.ktor.http.*
import io.ktor.routing.*
import kotlin.reflect.full.findAnnotation

inline fun <TRoute : OpenAPIRoute<TRoute>> TRoute.addRoute(path: String, crossinline fn: TRoute.() -> Unit) {
    if(path.endsWith('/')) {
        path.removeRange(path.length - 2, path.length - 1)
    }
    route(path, fn)
    route("$path/", fn)
}

inline fun <reified TResponse : Any> OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.jsonParams(body: OperationModel.() -> Unit) {
    val model = route.ktorRoute.application.openAPIGen.api.paths.get(route.ktorRoute.parent.toString())?.get
    model?.also { body.invoke(it) }
}

inline fun <reified MODEL: OperationModel> NormalOpenAPIRoute.jsonParams(body: MODEL.() -> Unit) {
    val path = ktorRoute.application.openAPIGen.api.paths.get(ktorRoute.parent.toString())
    path?.also {
        val method = (ktorRoute.selector as HttpMethodRouteSelector).method
        val model = when (method) {
            HttpMethod.Get -> it.get
            HttpMethod.Post -> it.post
            HttpMethod.Put -> it.put
            HttpMethod.Delete -> it.delete
            HttpMethod.Head -> it.head
            HttpMethod.Patch -> it.patch
            HttpMethod.Options -> it.options
            else -> null
        }
        if(model != null)
            body.invoke(model as MODEL)
    }
}

suspend inline fun <reified TResponse : Any> OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.respondOk(response: TResponse) {
    val statusCode = route.provider.ofType<StatusProvider>().lastOrNull()?.getStatusForType(getKType<BaseResponse<TResponse>>()) ?: BaseResponse::class.findAnnotation<Response>()?.statusCode?.let { HttpStatusCode.fromValue(it) } ?: HttpStatusCode.OK
    responder.respond(statusCode, BaseResponse(true, response, null) as Any, pipeline)
}

suspend inline fun <reified TResponse : Any> OpenAPIPipelineResponseContext<TResponse>.respondError(exception: RemoteException) {
    val statusCode = route.provider.ofType<StatusProvider>().lastOrNull()?.getStatusForType(getKType<BaseResponse<TResponse>>()) ?: TResponse::class.findAnnotation<Response>()?.statusCode?.let { HttpStatusCode.fromValue(it) } ?: exception.httpErrorCode
    responder.respond(statusCode, BaseResponse(false, null, exception.toErrorData()) as Any, pipeline)
}

inline fun <reified TParams : Any, reified TResponse : Any> NormalOpenAPIRoute.xGet(
    vararg modules: RouteOpenAPIModule,
    example: TResponse? = null,
    noinline body: suspend OpenAPIPipelineResponseContext
    <BaseResponse<TResponse>>.(TParams) -> Unit
): NormalOpenAPIRoute {
    val newBody: (suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TParams) -> Unit) = {
        try {
            body.invoke(this, it)
        } catch (e: Throwable) {
            respondError(UnknownException())
        }
    }
    val newExample : BaseResponse<TResponse>? = if(example!=null) BaseResponse(true, example, null) else null
    return get(*modules, example = newExample, body = newBody)
}

inline fun <reified TParams : Any, reified TResponse : Any, reified TRequest: Any> NormalOpenAPIRoute.xPost(
    vararg modules: RouteOpenAPIModule,
    example: TResponse? = null,
    exampleRequest: TRequest? = null,
    noinline body: suspend OpenAPIPipelineResponseContext
    <BaseResponse<TResponse>>.(TParams, TRequest) -> Unit
): NormalOpenAPIRoute {
    val newBody: (suspend OpenAPIPipelineResponseContext<BaseResponse<TResponse>>.(TParams, TRequest) -> Unit) = { params, req ->
        try {
            body.invoke(this, params, req)
        } catch (e: Throwable) {
            respondError(UnknownException())
        }
    }
    val newExample : BaseResponse<TResponse>? = if(example!=null) BaseResponse(true, example, null) else null
    return post(*modules, exampleResponse = newExample, exampleRequest = exampleRequest, body = newBody)
}