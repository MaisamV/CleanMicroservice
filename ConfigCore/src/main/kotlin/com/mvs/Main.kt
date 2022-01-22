package com.mvs

import com.mvs.di.GrpcDeliveryProvider
import com.mvs.di.KtorDeliveryProvider
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    thread {
        val delivery = GrpcDeliveryProvider().provide()
        delivery.start(args)
    }
    val delivery = KtorDeliveryProvider().provide()
    delivery.start(args)
}