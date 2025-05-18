package com.at3nas.ludya.domain.usecases.auth

import com.at3nas.ludya.data.AuthRepository
import com.google.firebase.auth.FirebaseUser


class GetCurrentUserUseCase(private val authRepo: AuthRepository) {
    fun invoke() : FirebaseUser? {
        return authRepo.getCurrentUser()
    }
}