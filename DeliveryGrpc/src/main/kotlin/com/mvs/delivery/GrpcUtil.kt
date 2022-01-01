package com.mvs.delivery

import com.google.protobuf.GeneratedMessageV3
import com.mvs.auth.UserClaim
import com.mvs.exception.BadRequestException
import com.mvs.exception.BaseException
import com.mvs.exception.PermissionDeniedException
import com.mvs.exception.handleError
import io.grpc.Status.Code
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.ProtoUtils
import io.grpc.stub.StreamObserver
import ir.sabaolgoo.ICommand
import ir.sabaolgoo.ICommandFactory
import ir.sabaolgoo.ICommandFactoryProvider

inline fun <reified TResponse: GeneratedMessageV3> respond(
    responseObserver: StreamObserver<TResponse>?,
    body: () -> TResponse
) {
    handleError({
        responseObserver?.onError(it.toGrpcException())
    }) {
        val reply = body.invoke()
        sendData(responseObserver, reply)
    }
}

inline fun <reified TCommand : ICommand> withFactory(
    factory: ICommandFactoryProvider,
    body: (ICommandFactory<TCommand>) -> Unit
) {
    body.invoke(factory.getCommandFactory(TCommand::class) as ICommandFactory<TCommand>)
}

inline fun <reified TCommand : ICommand> getFactory(
    factory: ICommandFactoryProvider,
): ICommandFactory<TCommand> {
    return factory.getCommandFactory(TCommand::class) as ICommandFactory<TCommand>
}

inline fun <reified TResponse: GeneratedMessageV3> sendData(responseObserver: StreamObserver<TResponse>?, response: TResponse) {
    responseObserver?.onNext(response)
    responseObserver?.onCompleted()
}

inline fun <reified T : BaseException> T.toErrorMessage(): ErrorMessage {
    return ErrorMessage.newBuilder().setCode(code).setErrorMessage(errorMessage).setLocalMessage(localMessage).build()
}

inline fun <reified T : BaseException> T.toGrpcStatusCode(): Code {
    return when (this::class) {
        BadRequestException::class -> Code.INVALID_ARGUMENT
        PermissionDeniedException::class -> Code.PERMISSION_DENIED
        else -> Code.INTERNAL
    }
}

inline fun <reified T : BaseException> T.toGrpcStatus(): io.grpc.Status {
    return toGrpcStatusCode().toStatus()
}

inline fun <reified T : BaseException> T.toGrpcException(): StatusRuntimeException {
    val errorKey: io.grpc.Metadata.Key<ErrorMessage> = ProtoUtils.keyForProto(ErrorMessage.getDefaultInstance())
    val metadata = io.grpc.Metadata()
    val error = toErrorMessage()
    metadata.put(errorKey, error);
    return toGrpcStatus().withDescription(" -::- errorCode=${error.code} -::- errorMessage=${error.errorMessage} -::- localMessage=${error.localMessage}").asRuntimeException(metadata)
}