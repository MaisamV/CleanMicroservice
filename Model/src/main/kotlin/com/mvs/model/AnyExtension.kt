package com.mvs.model

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


@OptIn(ExperimentalContracts::class)
inline fun <T: Any?> T.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
    }
    return this != null
}

@OptIn(ExperimentalContracts::class)
inline fun <T: Any?> T.isNull(): Boolean {
    contract {
        returns(false) implies (this@isNull != null)
    }
    return this == null
}

inline fun printStacktrace(msg: String?) {
    println(msg)
    Thread.dumpStack()
}