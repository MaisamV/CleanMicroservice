package com.mvs.delivery.service

import com.mvs.auth.UserClaim
import com.mvs.delivery.*
import com.mvs.health.IHealthCommand
import io.grpc.stub.StreamObserver
import ir.sabaolgoo.ICommandFactory
import ir.sabaolgoo.ICommandFactoryProvider

class HealthService : HealthGrpc.HealthImplBase() {
    override fun health(request: EmptyRequest?, responseObserver: StreamObserver<HealthDto>?) {
        respond(responseObserver) {
            CommandFactoryHolder.healthFactory.create(UserClaim()).checkHealth().toDto()
        }
    }
}