package com.mvs.service

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.mvs.delivery.IDelivery
import com.mvs.model.env
import com.mvs.service.health.healthRoutes
import com.mvs.service.health.pingRoute
import com.mvs.service.health.swaggerRoute
import com.mvs.service.util.ServiceInfoFactory
import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.modules.handlers.RouteHandler
import com.papsign.ktor.openapigen.route.apiRouting
import com.papsign.ktor.openapigen.schema.namer.DefaultSchemaNamer
import com.papsign.ktor.openapigen.schema.namer.SchemaNamer
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import ir.sabaolgoo.ICommandFactoryProvider
import kotlin.reflect.KType


class KtorDelivery(private val commandFactoryProvider: ICommandFactoryProvider): IDelivery {

    override fun start(args: Array<String>) {
        commandFactory = commandFactoryProvider
        EngineMain.main(args)
    }
}

lateinit var commandFactory: ICommandFactoryProvider
val projectName = env("project_name").let {
    if(it.isNullOrEmpty()){
        println("Project name can not be null.")
    }
    it!!
}
inline fun endpointPrefix(version: Int) = "/api/$projectName/v$version"
val maxVersion = 1
val versions = mutableListOf<Int>().apply { for(i in 1..maxVersion){this.add(i)} }

fun Application.addAllRoot() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header(HttpHeaders.ContentType)
        // header("any header") if you want to add any header
        allowCredentials = false
        allowNonSimpleContentTypes = true
        anyHost()
    }
    install(OpenAPIGen) {
        // basic info
        info {
            version = "0.9.0"
            title = "${projectName.uppercase()} API"
            description = "This document provides information about almost all APIs provided by ${projectName} server app."
            contact {
                name = "Developer"
                email = "maisam.vahidsafa@gmail.com"
            }
        }
        // describe the server, add as many as you want
        server("/") {
            description = "Test server"
        }
        //optional custom schema object namer
        replaceModule(DefaultSchemaNamer, object :SchemaNamer {
            val regex = Regex("[A-Za-z0-9_.]+")
            override fun get(type: KType) : String {
                return type.toString().replace(regex) {
                    it.value.split(".").last()
                }.replace(Regex(">|<|, "), "_")
            }
        })
    }
    install(ContentNegotiation) {
        jackson {
            registerModule(JavaTimeModule())
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }
    registerRoutes()
}

fun Application.registerRoutes() {
    routing {
        swaggerRoute()
        healthRoutes()
        pingRoute()
    }
    apiRouting {
        RouteHandler.factory = ServiceInfoFactory
    }
}