package com.mvs.service.exception

import io.ktor.http.*

class BadRequestException: BaseException(HttpStatusCode.BadRequest, 1, "Parameters are not filled or contain invalid values.", "مقادیر وارد شده صحیح نیست.") {
}