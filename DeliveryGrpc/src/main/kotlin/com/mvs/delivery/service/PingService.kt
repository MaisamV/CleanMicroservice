package com.mvs.delivery.service

import com.mvs.delivery.PingGrpc
import com.mvs.delivery.PingRequest
import com.mvs.delivery.PingResponse
import com.mvs.health.IPingCommand
import io.grpc.stub.StreamObserver

class PingService(val command: IPingCommand) : PingGrpc.PingImplBase() {
    override fun ping(req: PingRequest, responseObserver: StreamObserver<PingResponse?>) {
        val reply = PingResponse.newBuilder().setIsSuccessful(command.ping()).build()
        responseObserver.onNext(reply)
        responseObserver.onCompleted()
    }
}