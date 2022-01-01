package com.mvs.delivery.service

import com.mvs.delivery.*
import com.mvs.health.IHealthCommand
import io.grpc.stub.StreamObserver

class HealthService(private val command: IHealthCommand) : HealthGrpc.HealthImplBase() {
    override fun health(request: EmptyRequest?, responseObserver: StreamObserver<HealthDto>?) {
        respond(responseObserver) {
            command.checkHealth().toDto()
        }
    }
}