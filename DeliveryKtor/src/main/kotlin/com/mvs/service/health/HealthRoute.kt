package com.mvs.service.health

import com.mvs.auth.UserClaim
import com.mvs.health.IHealthCommand
import com.mvs.model.health.HealthInfo
import com.mvs.service.util.*
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import java.util.*

fun NormalOpenAPIRoute.healthRoutes() {
    serviceRoute<IHealthCommand> { factory ->
        addRoute("/api/health") {
            val code = 0x10001L
            xPost<Unit, HealthInfo, Unit> { _, _ ->
                val command = factory.create(UserClaim())
                respondOk(command.checkHealth())
            }.jsonParams<ServiceInfoModel> {
                created = GregorianCalendar(2021, 11, 23).time
                serviceCode = code
            }

            xGet<Unit, HealthInfo> {
                val command = factory.create(UserClaim())
                respondOk(command.checkHealth())
            }.jsonParams<ServiceInfoModel> {
                created = GregorianCalendar(2021, 11, 23).time
                serviceCode = code
            }
        }
    }
}