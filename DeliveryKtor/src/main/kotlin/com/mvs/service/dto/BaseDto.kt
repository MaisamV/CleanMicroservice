package com.mvs.service.dto

import com.papsign.ktor.openapigen.annotations.parameters.HeaderParam

open class BaseDto(
    @HeaderParam("Current user id") val sub: Long?,
    @HeaderParam("The user that service operate on") val `TARGET-USER-ID`: Long?,
    @HeaderParam("Current user role (SUPER_ADMIN, ADMIN, NORMAL, ANONYMOUS)") val role: String?
)


