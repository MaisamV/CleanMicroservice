package com.mvs.delivery

import com.mvs.exception.ErrorData
import com.mvs.model.health.HealthInfo

fun HealthInfo.toDto(): HealthResponse {
    return HealthResponse.newBuilder().also {
        it.canConnect = this.canConnect
        it.hasFunctionAccess = this.hasFunctionAccess
    }.build()
}

fun ErrorData.toDto(): com.mvs.delivery.ErrorData {
    return com.mvs.delivery.ErrorData.newBuilder().also {
        it.code = this.code
        it.errorMessage = this.errorMessage
        it.localMessage = this.localMessage
    }.build()
}