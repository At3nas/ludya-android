package com.at3nas.ludya.domain.usecases.auth

import com.at3nas.ludya.data.AuthRepository

class LogInWithEmailUseCase(private val authRepo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = authRepo.logInWithEmail(email, password)
}