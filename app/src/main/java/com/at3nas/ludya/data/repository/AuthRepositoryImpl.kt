package com.at3nas.ludya.data.repository

import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.repository.AuthRepository
import com.at3nas.ludya.data.network.service.AuthService
import com.at3nas.ludya.presentation.logIn.model.Login
import com.at3nas.ludya.presentation.signUp.model.SignUp
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun signUpWithEmail(user: SignUp): AuthResponse =
        authService.signUpWithEmail(user)

    override suspend fun logInWithEmail(user: Login): AuthResponse =
        authService.logInWithEmail(user)

    override suspend fun logOut() = authService.logOut()

    override fun getCurrentUser(): FirebaseUser? = authService.getCurrentUser()
}