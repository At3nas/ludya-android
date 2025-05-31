package com.at3nas.ludya.domain.repository

interface UserRepository {
    suspend fun getUsername(): Any?
}