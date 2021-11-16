package com.mvs.service

import com.fasterxml.jackson.annotation.JsonInclude
import com.mvs.delivery.IDelivery
import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand
import com.test.healthRoutes
import com.test.pingRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.cio.*

class KtorDelivery(private val health: IHealthCommand, private val ping: IPingCommand): IDelivery {

    override fun start(args: Array<String>) {
        healthCommand = health
        pingCommand = ping
        EngineMain.main(args)
    }
}

internal lateinit var healthCommand: IHealthCommand
internal lateinit var pingCommand: IPingCommand

fun Application.addAllRoot() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header(HttpHeaders.ContentType)
        // header("any header") if you want to add any header
        allowCredentials = true
        allowNonSimpleContentTypes = true
        anyHost()
    }
    install(ContentNegotiation) {
        jackson {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }
    registerRoutes()
}

fun Application.registerRoutes() {
    routing {
        healthRoutes(healthCommand)
        pingRoute(pingCommand)
    }
}