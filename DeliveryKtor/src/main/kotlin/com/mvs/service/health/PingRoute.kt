package com.mvs.service.health

import com.mvs.health.IPingCommand
import com.mvs.service.DocExample
import com.mvs.service.util.addRoute
import com.mvs.service.util.respondOk
import com.mvs.service.util.xGet
import io.ktor.application.*
import io.ktor.routing.*

fun Route.pingRoute(command: IPingCommand) {
    route("/api") {
        addRoute("/ping") {
            xGet(DocExample.getExamples) {
                call.respondOk(command.ping())
            }
        }
    }
}