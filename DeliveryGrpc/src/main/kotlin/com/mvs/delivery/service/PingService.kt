package com.mvs.delivery.service

import com.mvs.delivery.PingDto
import com.mvs.delivery.PingGrpc
import com.mvs.delivery.PingRequest
import com.mvs.delivery.respond
import com.mvs.health.IPingCommand
import io.grpc.stub.StreamObserver

class PingService(val command: IPingCommand) : PingGrpc.PingImplBase() {
    override fun ping(req: PingRequest, responseObserver: StreamObserver<PingDto>?) {
        respond(responseObserver, {
            PingDto.newBuilder()
                .setIsSuccessful(false)
                .setError(it)
                .build()
        }) {
            PingDto.newBuilder()
                .setIsSuccessful(true)
                .setData(command.ping())
                .build()
        }
    }
}