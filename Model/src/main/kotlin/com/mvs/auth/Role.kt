package com.mvs.auth

class Role(
    var id: Long? = null,
    var name: String? = null
) {
    companion object {
        val SUPER_ADMIN = Role(9223372036854775807, "SUPER_ADMIN")
        val ADMIN = Role(20000, "ADMIN")
        val NORMAL = Role(1, "NORMAL")
        val ANONYMOUS = Role(0, "ANONYMOUS")
    }
}