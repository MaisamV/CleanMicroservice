package com.mvs

import com.mvs.di.GrpcDeliveryProvider
import com.mvs.di.KtorDeliveryProvider
import com.mvs.model.env
import migrateDb
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    migrateDb(
        env("db_url")!!,
        env("db_superuser_name")!!,
        env("db_superuser_name")!!
    )
    thread {
        val delivery = GrpcDeliveryProvider().provide()
        delivery.start(args)
    }
    val delivery = KtorDeliveryProvider().provide()
    delivery.start(args)
}