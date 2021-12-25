package com.mvs.exception

class BadRequestException: BaseException(ExceptionCode.BAD_REQUEST.code, "Parameters are not filled or contain invalid values.", "مقادیر وارد شده صحیح نیست.")