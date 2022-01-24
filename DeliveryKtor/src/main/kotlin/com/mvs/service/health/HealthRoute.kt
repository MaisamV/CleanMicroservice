package com.mvs.service.health

import com.mvs.auth.UserClaim
import com.mvs.health.IHealthCommand
import com.mvs.service.commandFactory
import com.mvs.service.util.addRoute
import com.mvs.service.util.respondOk
import io.ktor.application.*
import io.ktor.routing.*

fun Route.healthRoutes() {
    addRoute("/api/health") {
        get {
            call.respondOk(commandFactory.getCommandFactory(IHealthCommand::class).create(UserClaim()).checkHealth())
        }
    }
}
