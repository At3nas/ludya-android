package com.at3nas.ludya.domain.model.user


data class User(
    val uuid: String?,
    val email: String?,
    val username: String?,
    val role: UserRole
)