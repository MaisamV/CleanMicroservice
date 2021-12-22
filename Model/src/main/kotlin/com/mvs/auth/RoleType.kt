package com.mvs.auth

enum class RoleType(val code: Long) {
    SUPER_ADMIN(0),
    ADMIN(1),
    NORMAL(20000),
    ANONYMOUS(Long.MAX_VALUE);

    companion object {
        fun from(code: Long): RoleType = RoleType.values().first { it.code == code }
    }
}