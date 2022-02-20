package com.mvs.exception

class DuplicateException: BaseException(ExceptionCode.DUPLICATE_VALUE.code, "Duplicate value.", "مقدار وارد شده تکراریست.") {
}