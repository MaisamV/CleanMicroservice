package com.mvs.exception

open class BaseErrorCause (
    val type: CauseType,
    val field: String
)