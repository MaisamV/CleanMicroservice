package com.mvs.auth

enum class RoleType(val code: Long) {
    SUPER_ADMIN(Long.MAX_VALUE),
    ADMIN(20000),
    NORMAL(1),
    ANONYMOUS(0);

    companion object {
        fun from(code: Long): RoleType = RoleType.values().first { it.code == code }
    }
}