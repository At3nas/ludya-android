package com.at3nas.ludya.data.network.response

import com.google.firebase.auth.FirebaseUser

sealed class AuthResponse {
    data class Error(val error: Exception): AuthResponse()
    data class Success(val user: FirebaseUser?): AuthResponse()
    //data object Loading: AuthResponse()
}