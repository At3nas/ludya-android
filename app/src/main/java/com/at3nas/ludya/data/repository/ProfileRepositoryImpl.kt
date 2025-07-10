package com.at3nas.ludya.data.repository

import com.at3nas.ludya.data.network.service.ProfileService
import com.at3nas.ludya.domain.model.profile.Profile
import com.at3nas.ludya.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRepository {
    override suspend fun getProfileById(userId: String): Profile? = profileService.getProfileById(userId)
    override suspend fun getUsername(): String? = profileService.getUsername()
    override suspend fun getDisplayName(): String? = profileService.getDisplayName()
    override suspend fun getCoins(): Long? = profileService.getCoins()
    override suspend fun getGems(): Long? = profileService.getGems()
    override suspend fun getLevel(): Long? = profileService.getLevel()
    override suspend fun getExp(): Long? = profileService.getExp()

    // UPDATE //
    override suspend fun updateDisplayName(newDisplayName: String) = profileService.updateDisplayName(newDisplayName)
    override suspend fun updateCoins(newCoins: Long) = profileService.updateCoins(newCoins)
    override suspend fun updateGems(newGems: Long) = profileService.updateGems(newGems)
    override suspend fun updateLevel(newLevel: Long) = profileService.updateLevel(newLevel)
    override suspend fun updateExp(newExp: Double) = profileService.updateExp(newExp)
}