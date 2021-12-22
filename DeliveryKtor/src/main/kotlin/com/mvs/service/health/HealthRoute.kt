package com.mvs.service.health

import com.mvs.auth.UserClaim
import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand
import com.mvs.model.health.HealthInfo
import com.mvs.service.commandFactory
import com.mvs.service.util.*
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import ir.sabaolgoo.ICommandFactory
import java.util.*

fun NormalOpenAPIRoute.healthRoutes() {
    addRoute("/api/health") {
        val code = 0x10001L
        xPost<Unit, HealthInfo, Unit> { _, _ ->
            val commandFactory: ICommandFactory<IHealthCommand> = commandFactory.getCommandFactory(IHealthCommand::class) as ICommandFactory<IHealthCommand>
            val command = commandFactory.create(UserClaim())
            respondOk(command.checkHealth())
        }.jsonParams<ServiceInfoModel> {
            created = GregorianCalendar(2021, 11, 23).time
            serviceCode = code
        }

        xGet<Unit, HealthInfo> {
            val commandFactory: ICommandFactory<IHealthCommand> = commandFactory.getCommandFactory(IHealthCommand::class) as ICommandFactory<IHealthCommand>
            val command = commandFactory.create(UserClaim())
            respondOk(command.checkHealth())
        }.jsonParams<ServiceInfoModel> {
            created = GregorianCalendar(2021, 11, 23).time
            serviceCode = code
        }
    }
}