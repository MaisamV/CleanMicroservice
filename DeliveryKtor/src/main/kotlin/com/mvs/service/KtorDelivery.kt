package com.mvs.service

import com.mvs.delivery.IDelivery
import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
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
    install(ContentNegotiation) {
        json()
    }
    registerRoutes()
}

fun Application.registerRoutes() {

}