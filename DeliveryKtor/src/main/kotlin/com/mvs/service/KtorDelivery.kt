package com.mvs.service

import com.fasterxml.jackson.annotation.JsonInclude
import com.mvs.delivery.IDelivery
import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand
import com.mvs.service.health.healthRoutes
import com.mvs.service.health.pingRoute
import io.bkbn.kompendium.Notarized.notarizedException
import io.bkbn.kompendium.models.meta.ResponseInfo
import io.bkbn.kompendium.routes.openApi
import io.bkbn.kompendium.routes.redoc
import io.bkbn.kompendium.swagger.swaggerUI
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.webjars.*

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
    install(Webjars)
    install(StatusPages) {
        notarizedException<Exception, ExceptionResponse>(
            info = ResponseInfo(
                HttpStatusCode.BadRequest,
                "Bad Things Happened",
                examples = mapOf("example" to ExceptionResponse("hey bad things happened sorry"))
            )
        ) {
            call.respond(HttpStatusCode.BadRequest, ExceptionResponse("Why you do dis?"))
        }
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
        openApi(oas)
        redoc(oas)
        swaggerUI()
        healthRoutes(healthCommand)
        pingRoute(pingCommand)
    }
}