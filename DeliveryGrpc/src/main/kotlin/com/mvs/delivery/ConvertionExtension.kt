package com.mvs.delivery

import com.mvs.HealthResponse
import com.mvs.model.health.HealthInfo

fun HealthInfo.toDto(): HealthResponse {
    return HealthResponse.newBuilder().also {
        it.canConnect = this.canConnect
        it.hasFunctionAccess = this.hasFunctionAccess
    }.build()
}