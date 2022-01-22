package com.mvs.service.health

import com.mvs.health.IHealthCommand
import com.mvs.model.health.HealthInfo
import com.mvs.service.dto.BaseDto
import com.mvs.service.util.*
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import java.util.*

fun NormalOpenAPIRoute.healthRoutes() {
    serviceRoute<IHealthCommand> { factory ->
        addRoute("/api/health") {
            val code = 0x10001L
            xAuthPost<IHealthCommand, BaseDto, HealthInfo, Unit>(factory) { command, claim, params, req ->
                respondOk(command.checkHealth())
            }.jsonParams<ServiceInfoModel> {
                created = GregorianCalendar(2021, 11, 23).time
                serviceCode = code
            }

            xAuthGet<IHealthCommand, BaseDto, HealthInfo>(factory) { command, claim, params ->
                respondOk(command.checkHealth())
            }.jsonParams<ServiceInfoModel> {
                created = GregorianCalendar(2021, 11, 23).time
                serviceCode = code
            }
        }
    }
}