package com.mvs.service.util

import io.bkbn.kompendium.Kompendium
import io.bkbn.kompendium.KompendiumPreFlight
import io.bkbn.kompendium.MethodParser
import io.bkbn.kompendium.models.meta.MethodInfo
import io.bkbn.kompendium.models.oas.OpenApiSpecPathItem
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

fun Route.addRoute(path: String, build: Route.() -> Unit) {
    if(path.endsWith('/')) {
        path.removeRange(path.length - 2, path.length - 1)
    }
    route(path, build)
    route("$path/", build)
}

inline fun <reified TParam : Any, reified TReq : Any, reified TResp : Any> Route.notarizedPatch(
    info: MethodInfo.PostInfo<TParam, TReq, TResp>,
    noinline body: PipelineInterceptor<Unit, ApplicationCall>
): Route {
    return KompendiumPreFlight.methodNotarizationPreFlight<TParam, TReq, TResp>() { paramType, requestType, responseType ->
        val path = Kompendium.calculatePath(this)
        Kompendium.openApiSpec.paths.getOrPut(path) { OpenApiSpecPathItem() }
        Kompendium.openApiSpec.paths[path]?.patch =
            MethodParser.parseMethodInfo(info, paramType, requestType, responseType)
        return method(HttpMethod.Patch) { handle(body) }
    }
}