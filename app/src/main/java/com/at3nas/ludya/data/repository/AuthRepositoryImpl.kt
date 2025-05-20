package com.at3nas.ludya.data.repository

import com.at3nas.ludya.domain.repository.AuthRepository
import com.at3nas.ludya.data.network.AuthService
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebase: AuthService
) : AuthRepository {
    override suspend fun signUpWithEmail(email: String, password: String): AuthResult? {
        return firebase.signUpWithEmail(email, password)
    }

    override suspend fun logInWithEmail(email: String, password: String): AuthResult? {
        return firebase.logInWithEmail(email, password)
    }

    override suspend fun logOut() {
        return firebase.logOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebase.getCurrentUser()
    }
}