package com.mvs.health

import com.mvs.model.health.HealthInfo
import com.mvs.repo.health.ConnectionTester
import com.mvs.repo.health.FunctionAccessTester

class HealthCommand(
    private val connectionTester: ConnectionTester,
    private val functionTester: FunctionAccessTester
    ): IHealthCommand {

    override fun checkHealth(): HealthInfo {
        return HealthInfo(
            connectionTester.canConnect(),
            functionTester.isFunctionsAccessible()
        )
    }

    override fun checkDbHealth() = connectionTester.canConnect()
}