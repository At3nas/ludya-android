package com.at3nas.ludya.domain.model

class NewUser(
    val email: String,
    val password: String,
    val confirmPassword: String,
) {
    val isTeacher: Boolean = false
}