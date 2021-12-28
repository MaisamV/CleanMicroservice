package com.mvs.delivery

import com.google.protobuf.GeneratedMessageV3
import com.mvs.exception.handleError
import com.mvs.exception.toErrorData
import io.grpc.stub.StreamObserver

inline fun <reified TResponse: GeneratedMessageV3> respond(responseObserver: StreamObserver<TResponse>?, errorBody: (ErrorData) -> TResponse, body: () -> TResponse) {
    handleError({
        val reply = errorBody.invoke(it.toErrorData().toDto())
        sendData(responseObserver, reply)
    }) {
        val reply = body.invoke()
        sendData(responseObserver, reply)
    }
}

inline fun <reified TResponse: GeneratedMessageV3> sendData(responseObserver: StreamObserver<TResponse>?, response: TResponse) {
    responseObserver?.onNext(response)
    responseObserver?.onCompleted()
}