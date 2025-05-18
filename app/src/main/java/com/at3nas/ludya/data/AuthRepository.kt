package com.at3nas.ludya.data
import com.at3nas.ludya.domain.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun logInWithEmail(email: String, password: String): AuthResult?
    suspend fun signUpWithEmail(email: String, password: String): AuthResult?
    suspend fun logOut()
    fun getCurrentUser(): FirebaseUser?
}