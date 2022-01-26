package com.mvs.exception

class ErrorData(
    val title: String,
    val details: List<String>,
    val cause: BaseErrorCause? = null,
    val code: Long? = null
)