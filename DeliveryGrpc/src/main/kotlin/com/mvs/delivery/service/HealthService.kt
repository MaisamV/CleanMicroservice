package com.mvs.delivery.service

import com.mvs.delivery.*
import com.mvs.health.IHealthCommand
import io.grpc.stub.StreamObserver

class HealthService(private val command: IHealthCommand) : HealthGrpc.HealthImplBase() {
    override fun health(req: HealthRequest, responseObserver: StreamObserver<HealthDto>?) {
        respond(responseObserver, {
            HealthDto.newBuilder()
                .setIsSuccessful(false)
                .setError(it)
                .build()
        }) {
            HealthDto.newBuilder()
                .setIsSuccessful(true)
                .setData(command.checkHealth().toDto())
                .build()
        }
    }
}