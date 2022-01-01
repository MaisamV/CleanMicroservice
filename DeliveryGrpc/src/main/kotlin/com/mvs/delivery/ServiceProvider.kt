package com.mvs.delivery

import com.mvs.delivery.service.HealthService
import com.mvs.delivery.service.PingService
import io.grpc.ServerBuilder

fun <T: ServerBuilder<T>> ServerBuilder<T>.addAllServices(server: GrpcDelivery): ServerBuilder<*> {
    return this
        .addService(PingService(server.commandFactoryProvider))
        .addService(HealthService(server.commandFactoryProvider))
}