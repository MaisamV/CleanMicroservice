package com.mvs.service.health

import com.mvs.health.IPingCommand
import com.mvs.service.commandFactory
import com.mvs.service.util.addRoute
import com.mvs.service.util.authGet
import com.mvs.service.util.ktorService
import com.mvs.service.util.respondOk
import io.ktor.application.*
import io.ktor.routing.*

fun Route.pingRoute() {
    ktorService(commandFactory.getCommandFactory(IPingCommand::class)) { factory ->
        addRoute("/api/ping") {
            authGet(factory) { command , claim ->
                call.respondOk(command.ping())
            }
        }
    }
}
