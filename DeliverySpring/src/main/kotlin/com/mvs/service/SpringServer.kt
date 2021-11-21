package com.mvs.service

import com.mvs.delivery.IDelivery
import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand

class SpringServer(healthCommand: IHealthCommand, pingCommand: IPingCommand) : IDelivery {
    companion object {
        lateinit var healthCommand: IHealthCommand
        lateinit var pingCommand: IPingCommand
    }

    init {
        SpringServer.healthCommand = healthCommand
        SpringServer.pingCommand = pingCommand
    }

    override fun start(args: Array<String>) {
        Application.run(args)
    }
}