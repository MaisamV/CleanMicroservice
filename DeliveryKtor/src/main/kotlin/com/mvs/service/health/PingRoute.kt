package com.test

import com.mvs.health.IPingCommand
import com.mvs.service.DocExample
import com.mvs.service.util.GsonProvider
import com.mvs.service.util.addRoute
import io.bkbn.kompendium.Notarized.notarizedGet
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

fun Route.pingRoute(command: IPingCommand) {
    route("/api") {
        addRoute("/ping") {
            notarizedGet(DocExample.getExamples) {
                handlePing(command)
            }
        }
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.handlePing(command: IPingCommand) {
    try {
        call.respondText(
            GsonProvider.gson.toJson(command.ping()),
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