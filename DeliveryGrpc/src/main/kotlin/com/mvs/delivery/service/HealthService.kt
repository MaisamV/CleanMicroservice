package com.mvs.delivery.service

import com.mvs.delivery.HealthGrpc
import com.mvs.delivery.HealthRequest
import com.mvs.delivery.HealthResponse
import com.mvs.delivery.toDto
import com.mvs.health.IHealthCommand
import io.grpc.stub.StreamObserver

class HealthService(private val command: IHealthCommand) : HealthGrpc.HealthImplBase() {
    override fun health(req: HealthRequest, responseObserver: StreamObserver<HealthResponse?>) {
        val healthInfo = command.checkHealth()
        responseObserver.onNext(healthInfo.toDto())
        responseObserver.onCompleted()
    }
}