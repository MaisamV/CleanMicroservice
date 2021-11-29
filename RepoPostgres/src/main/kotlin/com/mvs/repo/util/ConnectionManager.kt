package com.mvs.repo.util

import org.apache.commons.dbcp2.BasicDataSource
import org.postgresql.Driver
import java.sql.Connection
import java.sql.DriverManager

object ConnectionManager {
    val dataSource = initDb()

    fun <R> use(block: (Connection) -> R): R {
        return use(false, block)
    }

    fun <R> useAutoCommit(block: (Connection) -> R): R {
        return use(true, block)
    }

    private inline fun <R> use(autoCommit: Boolean, block: (Connection) -> R): R {
        return dataSource.connection.use {
            it.autoCommit = autoCommit
            block.invoke(it)
        }
    }

    private fun initDb(): BasicDataSource {
        DriverManager.registerDriver(Driver())

        val dataSource = BasicDataSource()
        dataSource.maxTotal = 90
        dataSource.maxIdle = 90
        dataSource.url = System.getenv("db_url") //example: "jdbc:postgresql://192.168.240.173:5432/postgres"
        dataSource.username = System.getenv("db_user_name")
        dataSource.password = System.getenv("db_user_pass")
        return dataSource
    }
}

inline fun <T : AutoCloseable?, R> T.use(block: (T) -> R): R {
    var exception: Throwable? = null
    try {
        return block(this)
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        when {
            this == null -> {}
            exception == null -> close()
            else ->
                try {
                    close()
                } catch (closeException: Throwable) {
                }
        }
    }
}
