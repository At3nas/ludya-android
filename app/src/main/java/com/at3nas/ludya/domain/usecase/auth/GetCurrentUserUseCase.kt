package com.at3nas.ludya.domain.usecase.auth

import com.at3nas.ludya.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser


class GetCurrentUserUseCase(private val authRepo: AuthRepository) {
    fun invoke() : FirebaseUser? {
        return authRepo.getCurrentUser()
    }
}