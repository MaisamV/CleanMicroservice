package com.mvs.service.health

import com.mvs.health.IHealthCommand
import com.mvs.model.health.HealthInfo
import com.mvs.service.util.*
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import java.util.*

fun NormalOpenAPIRoute.healthRoutes(command: IHealthCommand) {
    addRoute("/api/health") {
        val code = 0x10001L
        xPost<Unit, HealthInfo, Unit> { _, _ ->
            respondOk(command.checkHealth())
        }.jsonParams<ServiceInfoModel> {
            created = GregorianCalendar(2021, 11, 23).time
            serviceCode = code
        }

        xGet<Unit, HealthInfo> {
            respondOk(command.checkHealth())
        }.jsonParams<ServiceInfoModel> {
            created = GregorianCalendar(2021, 11, 23).time
            serviceCode = code
        }
    }
}