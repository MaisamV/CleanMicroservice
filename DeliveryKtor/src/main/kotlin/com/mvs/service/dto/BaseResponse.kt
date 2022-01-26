package com.mvs.service.dto

import com.mvs.exception.ErrorData

class BaseResponse<T>(
    var data: T? = null,
    var errors: List<ErrorData>? = null,
    var message: ResponseMessage? = null,
    var stack: String? = null,
    val type: String = "SabaTamin"
)