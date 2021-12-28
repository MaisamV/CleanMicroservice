package com.mvs.service.dto

import com.mvs.exception.ErrorData

class BaseResponse<T>(
    var isSuccessful: Boolean,
    var data: T?,
    var error: ErrorData?
)