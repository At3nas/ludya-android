package com.at3nas.ludya.data.repository

import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.repository.AuthRepository
import com.at3nas.ludya.data.network.service.AuthService
import com.at3nas.ludya.domain.model.NewUser
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebase: AuthService
) : AuthRepository {
    override suspend fun signUpWithEmail(user: NewUser): AuthResponse =
        firebase.signUpWithEmail(user)

    override suspend fun logInWithEmail(email: String, password: String): AuthResponse =
        firebase.logInWithEmail(email, password)

    override suspend fun logOut() = firebase.logOut()

    override fun getCurrentUser(): FirebaseUser? = firebase.getCurrentUser()
}