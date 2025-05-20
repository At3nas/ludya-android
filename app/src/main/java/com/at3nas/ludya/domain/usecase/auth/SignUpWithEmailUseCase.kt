package com.at3nas.ludya.domain.usecase.auth

import com.at3nas.ludya.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        val isSignedUp = authRepo.signUpWithEmail(email, password) != null

        return if (isSignedUp) {
            print("User created successfully")
            true
        } else {
            false
        }
    }
}