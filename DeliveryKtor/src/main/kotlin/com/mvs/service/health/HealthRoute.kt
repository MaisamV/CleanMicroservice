package com.test

import com.mvs.health.IHealthCommand
import com.mvs.service.DocExample
import com.mvs.service.util.GsonProvider
import com.mvs.service.util.addRoute
import io.bkbn.kompendium.Notarized.notarizedGet
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

fun Route.healthRoutes(command: IHealthCommand) {
    route("/api") {
        addRoute("/health") {
            notarizedGet(DocExample.getExamples) {
                handleHealth(command)
            }
        }
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.handleHealth(command: IHealthCommand) {
    try {
        call.respondText(
            GsonProvider.gson.toJson(command.checkHealth()),
            ContentType.Application.Json,
            HttpStatusCode.OK
        )
    } catch (e: Exception) {
        e.printStackTrace()
        call.respond(
            HttpStatusCode.InternalServerError
        )
    }
}