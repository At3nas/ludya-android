package com.at3nas.ludya.data.repository

import com.at3nas.ludya.data.network.service.UserService
import com.at3nas.ludya.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun getEmail(): String? = userService.getEmail()
    override suspend fun getRole(): String? = userService.getRole()
    override suspend fun getUsername(): String? = userService.getUsername()
    override suspend fun getUid(): String? = userService.getUid()
}