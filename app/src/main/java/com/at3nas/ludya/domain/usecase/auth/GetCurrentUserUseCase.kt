package com.at3nas.ludya.domain.usecase.auth

import com.at3nas.ludya.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class GetCurrentUserUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {
    fun invoke(): FirebaseUser? = authRepo.getCurrentUser()
}