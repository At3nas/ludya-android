package com.at3nas.ludya.domain.repository

import com.at3nas.ludya.domain.model.profile.Profile

interface ProfileRepository {
    suspend fun getProfileById(userId: String): Profile?
    suspend fun getUsername(): String?
    suspend fun getDisplayName(): String?
    suspend fun getCoins(): Long?
    suspend fun getGems(): Long?
    suspend fun getLevel(): Long?
    suspend fun getExp(): Long?

    // UPDATE //
    suspend fun updateDisplayName(newDisplayName: String)
    suspend fun updateCoins(newCoins: Long)
    suspend fun updateGems(newGems: Long)
    suspend fun updateLevel(newLevel: Long)
    suspend fun updateExp(newExp: Double)
}