package com.mvs.service.exception

import io.ktor.http.*

abstract class RemoteException(val httpErrorCode: HttpStatusCode, val code: Long, val errorMessage: String, val localMessage: String)