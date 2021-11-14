package com.mvs.delivery

import com.mvs.HealthResponse
import com.mvs.model.health.HealthInfo

fun HealthInfo.toDto(): HealthResponse {
    return HealthResponse.newBuilder().apply {
        canConnect = this.canConnect
        hasFunctionAccess = this.hasFunctionAccess
    }.build()
}