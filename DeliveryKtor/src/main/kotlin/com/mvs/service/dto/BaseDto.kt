package com.mvs.service.dto

import com.mvs.auth.RoleType
import com.papsign.ktor.openapigen.annotations.parameters.HeaderParam

open class BaseDto(
    @HeaderParam("Current user id") val `USER-ID`: Long?,
    @HeaderParam("The user that service operate on") val `TARGET-USER-ID`: Long?,
    @HeaderParam("Current user role") val `USER-ROLE`: RoleType?
)


