package com.mvs.service.dto

class BaseResponse<T>(
    var isSuccessful: Boolean,
    var data: T?,
    var error: ErrorData?
)