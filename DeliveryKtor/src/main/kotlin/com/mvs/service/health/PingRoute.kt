package com.mvs.service.health

import com.mvs.health.IPingCommand
import com.mvs.service.util.*
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import java.util.*

fun NormalOpenAPIRoute.pingRoute(command: IPingCommand) {
    addRoute("/api/ping") {
        val code = 0x10000L
        xGet<Unit, Boolean> {
            respondOk(command.ping())
        }.jsonParams<ServiceInfoModel> {
            created = GregorianCalendar(2021, 11, 23).time
            serviceCode = code
        }
    }
}