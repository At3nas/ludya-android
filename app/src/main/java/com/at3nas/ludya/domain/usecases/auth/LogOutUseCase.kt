package com.at3nas.ludya.domain.usecases.auth

import com.at3nas.ludya.data.AuthRepository

class LogOutUseCase(private val authRepo: AuthRepository) {
    suspend operator fun invoke() = authRepo.logOut()
}