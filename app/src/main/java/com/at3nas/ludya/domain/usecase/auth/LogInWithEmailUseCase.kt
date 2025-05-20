package com.at3nas.ludya.domain.usecase.auth

import com.at3nas.ludya.domain.repository.AuthRepository

class LogInWithEmailUseCase(private val authRepo: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = authRepo.logInWithEmail(email, password)
}