package com.at3nas.ludya.presentation.signUp.model

import com.at3nas.ludya.domain.model.user.UserRole

data class SignUp(
    val email: String,
    val username: String,
    val password: String,
    val confirmPassword: String,
    val role: UserRole
)
