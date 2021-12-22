package com.mvs.di

import com.mvs.auth.UserClaim
import com.mvs.health.IPingCommand
import com.mvs.health.PingCommand
import ir.sabaolgoo.ICommandFactory

class PingCommandFactory: ICommandFactory<IPingCommand> {
    override fun create(userClaim: UserClaim): IPingCommand {
        return PingCommand()
    }
}