package com.mvs.service.util

import io.ktor.routing.*

fun Route.addRoute(path: String, build: Route.() -> Unit) {
    if(path.endsWith('/')) {
        path.removeRange(path.length - 2, path.length - 1)
    }
    route(path, build)
    route("$path/", build)
}