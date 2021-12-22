package com.mvs.auth

class UserClaim(
    val userId: Long? = null,
    val roleList: List<RoleType> = emptyList()
)