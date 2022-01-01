package com.mvs.delivery.service

import com.mvs.auth.UserClaim
import com.mvs.delivery.*
import com.mvs.health.IPingCommand
import io.grpc.stub.StreamObserver
import ir.sabaolgoo.ICommandFactory
import ir.sabaolgoo.ICommandFactoryProvider

class PingService : PingGrpc.PingImplBase() {
    override fun ping(request: EmptyRequest?, responseObserver: StreamObserver<BoolDto>?) {
        respond(responseObserver) {
            CommandFactoryHolder.pingFactory.create(UserClaim()).ping().toDto()
        }
    }
}