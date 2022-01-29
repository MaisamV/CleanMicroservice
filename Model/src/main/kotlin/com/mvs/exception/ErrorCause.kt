package com.mvs.exception

open class ErrorCause (
    val type: CauseType,
    val field: String?,
    val row: Long?
)