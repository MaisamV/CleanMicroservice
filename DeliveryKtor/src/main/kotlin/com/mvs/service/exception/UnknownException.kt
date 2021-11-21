package com.mvs.service.exception

import io.ktor.http.*

class UnknownException: RemoteException(HttpStatusCode.InternalServerError, 0,  "Something is wrong", "مشکلی در سیستم به وجود آماده، در حال بررسی مشکل هستیم.")