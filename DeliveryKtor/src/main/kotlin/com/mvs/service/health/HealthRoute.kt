package com.mvs.service.health

import com.mvs.health.IHealthCommand
import com.mvs.service.DocExample
import com.mvs.service.util.addRoute
import com.mvs.service.util.respondOk
import com.mvs.service.util.xGet
import io.ktor.application.*
import io.ktor.routing.*

fun Route.healthRoutes(command: IHealthCommand) {
    route("/api") {
        addRoute("/health") {
            xGet(DocExample.getExamples) {
                call.respondOk(command.checkHealth())
            }
        }
    }
}