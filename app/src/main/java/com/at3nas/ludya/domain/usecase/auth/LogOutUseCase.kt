package com.at3nas.ludya.domain.usecase.auth

import com.at3nas.ludya.domain.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {
    suspend operator fun invoke() = authRepo.logOut()
}