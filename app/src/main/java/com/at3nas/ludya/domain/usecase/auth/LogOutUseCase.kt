package com.at3nas.ludya.domain.usecase.auth

import com.at3nas.ludya.domain.repository.AuthRepository

class LogOutUseCase(private val authRepo: AuthRepository) {
    suspend operator fun invoke() = authRepo.logOut()
}