package com.mvs.service.dto

import kotlin.properties.Delegates

enum class PingErrorSheet {
    PING_EXCEPTION(0, "Something is wrong", "مشکلی در سیستم به وجود آماده، در حال بررسی مشکل هستیم.");

    private var code by Delegates.notNull<Long>()
    private var message by Delegates.notNull<String>()
    private var localMessage by Delegates.notNull<String>()

    private constructor(code: Long, message: String, localMessage: String) {
        this.code = code
        this.message = message
        this.localMessage = localMessage
    }

    public fun toErrorData() = createError(code, message, localMessage)
    private fun createError(code: Long, message: String, localMessage: String) = ErrorData(ErrorType.PING, code, message, localMessage)
}