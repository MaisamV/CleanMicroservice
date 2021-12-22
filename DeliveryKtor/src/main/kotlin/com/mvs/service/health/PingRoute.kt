package com.mvs.service.health

import com.mvs.auth.UserClaim
import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand
import com.mvs.service.commandFactory
import com.mvs.service.util.*
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import ir.sabaolgoo.ICommandFactory
import java.util.*

fun NormalOpenAPIRoute.pingRoute() {
    addRoute("/api/ping") {
        val code = 0x10000L
        xGet<Unit, Boolean> {
            val commandFactory: ICommandFactory<IPingCommand> = commandFactory.getCommandFactory(IPingCommand::class) as ICommandFactory<IPingCommand>
            val command = commandFactory.create(UserClaim())
            respondOk(command.ping())
        }.jsonParams<ServiceInfoModel> {
            created = GregorianCalendar(2021, 11, 23).time
            serviceCode = code
        }
    }
}