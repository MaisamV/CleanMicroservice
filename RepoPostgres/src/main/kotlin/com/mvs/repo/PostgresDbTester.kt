package com.mvs.repo

import com.mvs.repo.health.ConnectionTester
import com.mvs.repo.health.FunctionAccessTester
import com.mvs.repo.util.ConnectionManager
import com.mvs.repo.util.DslManager
import com.repo.Routines

class PostgresDbTester: ConnectionTester, FunctionAccessTester {
    override fun canConnect(): Boolean {
        return ConnectionManager.dataSource.connection.isValid(5)
    }

    override fun isFunctionsAccessible(): Boolean {
        var result = false
        try {
            val check1 = DslManager.transaction {
                Routines.checkFunction(configuration())
            }
            result = check1 == 1
        } catch (ignored: Exception) {}
        return result
    }
}