package ir.sabaolgoo

import kotlin.reflect.KClass

interface ICommandFactoryProvider {
    fun <TCommand : ICommand> getCommandFactory(c: KClass<TCommand>): ICommandFactory<*>?
}