package com.mvs.repo.util

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL

object DslManager {
    fun <R> transaction(block: DSLContext.() -> R): R? {
        return ConnectionManager.use {
            try {
                val result = block(DSL.using(it, SQLDialect.POSTGRES))
                it.commit()
                result
            } catch (e: Exception) {
                it.rollback()
                throw e
            }
        }
    }
}