package com.at3nas.ludya.data.repository

import com.at3nas.ludya.data.network.service.AccountDeletionService
import com.at3nas.ludya.domain.repository.AccountDeletionRepository
import javax.inject.Inject

class AccountDeletionRepositoryImpl @Inject constructor(
    private val accountDeletionService: AccountDeletionService
) : AccountDeletionRepository {
    override fun deleteAccount() = accountDeletionService.deleteAccount()
}