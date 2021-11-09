package com.mvs.di

import com.mvs.repo.PostgresDbTester

class PostgresRepoProvider {
    fun provideDbTester(): PostgresDbTester {
        return PostgresDbTester()
    }
}