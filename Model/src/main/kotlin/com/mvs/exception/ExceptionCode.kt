package com.mvs.exception

enum class ExceptionCode(val code: Long) {
    UNKNOWN(0),
    BAD_REQUEST(1),
    PERMISSION_DENIED(2),
    DUPLICATE_VALUE(3),
    ;
}