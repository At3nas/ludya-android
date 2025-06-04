package com.at3nas.ludya.data.repository

import com.at3nas.ludya.data.network.service.ProfileService
import com.at3nas.ludya.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRepository {
    override suspend fun getUsername(): String? = profileService.getUsername()
    override suspend fun getDisplayName(): String? = profileService.getDisplayName()
    override suspend fun getCoins(): Long? = profileService.getCoins()
    override suspend fun getGems(): Long? = profileService.getGems()
    override suspend fun getLevel(): Long? = profileService.getLevel()
    override suspend fun getXp(): Long? = profileService.getXp()

    // UPDATE //
    override suspend fun updateDisplayName(newDisplayName: String) = profileService.updateDisplayName(newDisplayName)
    override suspend fun updateCoins(newCoins: Long) = profileService.updateCoins(newCoins)
    override suspend fun updateGems(newGems: Long) = profileService.updateGems(newGems)
    override suspend fun updateLevel(newLevel: Long) = profileService.updateLevel(newLevel)
    override suspend fun updateXp(newXp: Double) = profileService.updateXp(newXp)
}