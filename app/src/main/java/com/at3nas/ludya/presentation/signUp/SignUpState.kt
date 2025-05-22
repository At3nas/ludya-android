package com.at3nas.ludya.presentation.signUp

data class SignUpState (
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true,
    val isValidConfirmPassword: Boolean = true
) {
    fun isSignedUp() = isValidEmail && isValidPassword && isValidConfirmPassword
}