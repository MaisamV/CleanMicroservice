package com.mvs.delivery.service

import com.mvs.delivery.*
import com.mvs.health.IPingCommand
import io.grpc.stub.StreamObserver

class PingService(val command: IPingCommand) : PingGrpc.PingImplBase() {
    override fun ping(request: EmptyRequest?, responseObserver: StreamObserver<BoolDto>?) {
        respond(responseObserver) {
            command.ping().toDto()
        }
    }
}