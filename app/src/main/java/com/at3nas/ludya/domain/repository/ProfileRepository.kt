package com.at3nas.ludya.domain.repository

interface ProfileRepository {
    suspend fun getUsername(): String?
    suspend fun getDisplayName(): String?
    suspend fun getCoins(): Long?
    suspend fun getGems(): Long?
    suspend fun getLevel(): Long?
    suspend fun getXp(): Long?
    // UPDATE //
    suspend fun updateDisplayName(newDisplayName: String)
    suspend fun updateCoins(newCoins: Long)
    suspend fun updateGems(newGems: Long)
    suspend fun updateLevel(newLevel: Long)
    suspend fun updateXp(newXp: Double)
}