package com.at3nas.ludya.domain.repository
import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.model.NewUser
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun logInWithEmail(email: String, password: String): AuthResponse
    suspend fun signUpWithEmail(user: NewUser): AuthResponse
    suspend fun logOut()
    fun getCurrentUser(): FirebaseUser?
}