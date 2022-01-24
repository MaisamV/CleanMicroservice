package com.mvs.service.health

import com.mvs.health.IHealthCommand
import com.mvs.service.commandFactory
import com.mvs.service.util.addRoute
import com.mvs.service.util.authGet
import com.mvs.service.util.ktorService
import com.mvs.service.util.respondOk
import io.ktor.application.*
import io.ktor.routing.*

fun Route.healthRoutes() {
    ktorService(commandFactory.getCommandFactory(IHealthCommand::class)) { factory ->
        addRoute("/api/health") {
            authGet(factory) { command , claim ->
                call.respondOk(command.checkHealth())
            }
        }
    }
}
