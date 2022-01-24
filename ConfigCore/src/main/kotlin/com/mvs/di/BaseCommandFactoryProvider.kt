package com.mvs.di

import com.mvs.health.IHealthCommand
import com.mvs.health.IPingCommand
import ir.sabaolgoo.ICommand
import ir.sabaolgoo.ICommandFactory
import ir.sabaolgoo.ICommandFactoryProvider
import kotlin.reflect.KClass

class BaseCommandFactoryProvider: ICommandFactoryProvider {
    override fun <TCommand : ICommand> getCommandFactory(c: KClass<TCommand>): ICommandFactory<TCommand> {
        return when (c) {
            IPingCommand::class -> PingCommandFactory()
            IHealthCommand::class -> HealthCommandFactory()
            else -> throw IllegalArgumentException()
        } as ICommandFactory<TCommand>
    }
}