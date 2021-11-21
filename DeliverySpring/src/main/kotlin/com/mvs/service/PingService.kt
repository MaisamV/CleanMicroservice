package com.mvs.service

import com.mvs.health.IPingCommand
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PingService {

    private val pingCommand: IPingCommand = SpringServer.pingCommand

    @GetMapping("/api/ping")
    fun ping(): Boolean {
        return pingCommand.ping()
    }
}