package com.mvs.health

import com.mvs.model.health.HealthInfo
import ir.sabaolgoo.ICommand

interface IHealthCommand: ICommand {
    fun checkHealth(): HealthInfo
    fun checkDbHealth(): Boolean
}