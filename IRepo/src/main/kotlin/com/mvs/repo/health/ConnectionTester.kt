package com.mvs.repo.health

interface ConnectionTester {
    fun canConnect(): Boolean
}