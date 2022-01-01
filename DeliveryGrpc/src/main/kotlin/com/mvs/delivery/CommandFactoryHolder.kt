package com.mvs.delivery

import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand
import ir.sabaolgoo.ICommandFactory
import ir.sabaolgoo.ICommandFactoryProvider

object CommandFactoryHolder {

    lateinit var pingFactory: ICommandFactory<IPingCommand>
    lateinit var healthFactory: ICommandFactory<IHealthCommand>
    private lateinit var commandFactoryProvider: ICommandFactoryProvider

    fun setCommandFactoryProvider(provider: ICommandFactoryProvider) {
        commandFactoryProvider = provider
        pingFactory = getFactory(commandFactoryProvider)
        healthFactory = getFactory(commandFactoryProvider)
    }

    fun getCommandFactoryProvider(): ICommandFactoryProvider = commandFactoryProvider
}