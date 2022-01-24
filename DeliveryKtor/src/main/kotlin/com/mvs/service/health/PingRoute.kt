package com.mvs.service.health

import com.mvs.auth.UserClaim
import com.mvs.health.IPingCommand
import com.mvs.service.commandFactory
import com.mvs.service.util.addRoute
import com.mvs.service.util.respondOk
import io.ktor.application.*
import io.ktor.routing.*

fun Route.pingRoute() {
    addRoute("/api/ping") {
        get {
            call.respondOk(commandFactory.getCommandFactory(IPingCommand::class).create(UserClaim()).ping())
        }
    }
}
