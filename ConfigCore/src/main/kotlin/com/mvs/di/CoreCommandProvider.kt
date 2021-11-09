package com.mvs.di

import com.mvs.health.HealthCommand
import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand
import com.mvs.health.PingCommand

class CoreCommandProvider {
    fun provideHealth(): IHealthCommand {
        val connectionTester = PostgresRepoProvider().provideDbTester()
        return HealthCommand(connectionTester, connectionTester)
    }

    fun providePing(): IPingCommand {
        return PingCommand()
    }
}