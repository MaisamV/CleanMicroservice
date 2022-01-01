package com.mvs.delivery.service

import com.mvs.auth.UserClaim
import com.mvs.delivery.*
import com.mvs.health.IPingCommand
import io.grpc.stub.StreamObserver
import ir.sabaolgoo.ICommandFactory
import ir.sabaolgoo.ICommandFactoryProvider

class PingService(private val commandFactoryProvider: ICommandFactoryProvider) : PingGrpc.PingImplBase() {

    private val commandFactory: ICommandFactory<IPingCommand> = getFactory(commandFactoryProvider)

    override fun ping(request: EmptyRequest?, responseObserver: StreamObserver<BoolDto>?) {
        respond(responseObserver) {
            commandFactory.create(UserClaim()).ping().toDto()
        }
    }
}