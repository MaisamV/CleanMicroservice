package com.mvs.health

import com.mvs.model.health.HealthInfo

interface IHealthCommand {
    fun checkHealth(): HealthInfo
    fun checkDbHealth(): Boolean
}