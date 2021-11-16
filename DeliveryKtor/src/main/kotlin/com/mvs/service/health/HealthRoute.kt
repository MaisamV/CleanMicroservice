package com.mvs.service.health

import com.mvs.health.IHealthCommand
import com.mvs.service.DocExample
import com.mvs.service.dto.PingErrorSheet
import com.mvs.service.util.addRoute
import com.mvs.service.util.respondError
import com.mvs.service.util.respondOk
import io.bkbn.kompendium.Notarized.notarizedGet
import io.ktor.application.*
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
        call.respondOk(
            command.checkHealth()
        )
    } catch (e: Exception) {
        e.printStackTrace()
        call.respondError(
            PingErrorSheet.PING_EXCEPTION.toErrorData()
        )
    }
}