package com.mvs.delivery

import com.mvs.model.health.HealthInfo

fun HealthInfo.toDto(): HealthDto {
    return HealthDto.newBuilder().also {
        it.canConnect = this.canConnect
        it.hasFunctionAccess = this.hasFunctionAccess
    }.build()
}

fun Boolean.toDto(): BoolDto {
    return BoolDto.newBuilder()
        .setData(this)
        .build()
}