package com.at3nas.ludya.domain.repository

import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.presentation.logIn.model.Login
import com.at3nas.ludya.presentation.signUp.model.SignUp
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun logInWithEmail(user: Login): AuthResponse
    suspend fun signUpWithEmail(user: SignUp): AuthResponse
    suspend fun logOut()
    fun getCurrentUser(): FirebaseUser?
}