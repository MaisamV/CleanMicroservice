package com.mvs.delivery

import io.grpc.Server
import io.grpc.ServerBuilder
import ir.sabaolgoo.ICommandFactoryProvider
import java.util.concurrent.TimeUnit


class GrpcDelivery(val commandFactoryProvider: ICommandFactoryProvider): IDelivery {

    private lateinit var server: Server

    override fun start(args: Array<String>) {
        CommandFactoryHolder.setCommandFactoryProvider(commandFactoryProvider)
        /* The port on which the server should run */
        val port = 50051
        server = ServerBuilder.forPort(port)
            .addAllServices(this)
            .build()
            .start()
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down")
                try {
                    this@GrpcDelivery.stop()
                } catch (e: InterruptedException) {
                    e.printStackTrace(System.err)
                }
                System.err.println("*** server shut down")
            }
        })
        blockUntilShutdown()
    }

    private fun stop() {
        server.shutdown().awaitTermination(30, TimeUnit.SECONDS)
    }

    private fun blockUntilShutdown() {
        server.awaitTermination()
    }
}