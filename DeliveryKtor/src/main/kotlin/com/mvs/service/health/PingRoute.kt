package com.mvs.service.health

import com.mvs.health.IPingCommand
import com.mvs.service.DocExample
import com.mvs.service.dto.HealthErrorSheet
import com.mvs.service.util.addRoute
import com.mvs.service.util.respondError
import com.mvs.service.util.respondOk
import io.bkbn.kompendium.Notarized.notarizedGet
import io.ktor.application.*
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
        call.respondOk(
            command.ping()
        )
    } catch (e: Exception) {
        e.printStackTrace()
        call.respondError(
            HealthErrorSheet.HEALTH_EXCEPTION.toErrorData()
        )
    }
}