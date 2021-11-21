package com.mvs.service

import com.mvs.health.IHealthCommand
import com.mvs.model.health.HealthInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthService {

    private val healthCommand: IHealthCommand = SpringServer.healthCommand

    @GetMapping("/api/health")
    fun health(): HealthInfo {
        return healthCommand.checkHealth()
    }
}