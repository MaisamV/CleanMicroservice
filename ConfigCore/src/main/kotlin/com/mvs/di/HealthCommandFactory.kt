package com.mvs.di

import com.mvs.auth.UserClaim
import com.mvs.health.HealthCommand
import com.mvs.health.IHealthCommand
import com.mvs.repo.PostgresDbTester
import ir.sabaolgoo.ICommandFactory

class HealthCommandFactory: ICommandFactory<IHealthCommand> {
    override fun create(userClaim: UserClaim): IHealthCommand {
        val tester = PostgresDbTester()
        return HealthCommand(tester, tester)
    }
}