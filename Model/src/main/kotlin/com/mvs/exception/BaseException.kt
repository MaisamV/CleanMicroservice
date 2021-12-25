package com.mvs.exception

abstract class BaseException protected constructor(val code: Long, val errorMessage: String, val localMessage: String)