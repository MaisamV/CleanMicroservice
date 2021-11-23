package com.mvs.service.health

import com.papsign.ktor.openapigen.openAPIGen
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.swaggerRoute() {
    route("/") {
        get("/openapi.json") {
            call.respond(application.openAPIGen.api.serialize())
        }
        get("/") {
            call.respondRedirect("/swagger-ui/index.html?url=/openapi.json", true)
        }
    }
}