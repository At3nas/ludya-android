package com.at3nas.ludya.domain.repository

interface UserRepository {
    suspend fun getEmail(): String?
    suspend fun getRole(): String?
    suspend fun getUsername(): String?
    suspend fun getUid(): String?
}