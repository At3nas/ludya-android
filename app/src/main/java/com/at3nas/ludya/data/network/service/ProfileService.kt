package com.at3nas.ludya.data.network.service

import android.util.Log
import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.domain.model.FirestoreCollections
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileService @Inject constructor(
    private val firebase: FirebaseClient,
    private val userService: UserService
) {
    private val profileCollection = firebase.db.collection(FirestoreCollections.COLLECTION_PROFILE)

    private suspend fun getProfileData(): DocumentSnapshot? {
        return profileCollection
            .document(userService.getUid().toString())
            .get()
            .await()
    }

    // GET FIELDS //
    suspend fun getUsername(): String? {
        return userService.getUsername()
    }

    suspend fun getDisplayName(): String? {
        return getProfileData()?.getString("displayName")
    }

    suspend fun getCoins(): Long? {
        return getProfileData()?.getLong("currency.coins")
    }

    suspend fun getGems(): Long? {
        return getProfileData()?.getLong("currency.gems")
    }

    suspend fun getLevel(): Long? {
        return getProfileData()?.getLong("level.level")
    }

    suspend fun getXp(): Long? {
        return getProfileData()?.getLong("level.xp")
    }

    // SET FIELDS //
    suspend fun updateDisplayName(newDisplayName: String) {
        profileCollection
            .document(userService.getUid().toString())
            .update("displayName", newDisplayName)
    }

    suspend fun updateCoins(newCoins: Long) {
        profileCollection
            .document(userService.getUid().toString())
            .update("currency.coins", newCoins)
    }

    suspend fun updateGems(newGems: Long) {
        profileCollection
            .document(userService.getUid().toString())
            .update("currency.gems", newGems)
    }

    suspend fun updateLevel(newLevel: Long) {
        profileCollection
            .document(userService.getUid().toString())
            .update("level.level", newLevel)
    }

    suspend fun updateXp(newXp: Double) {
        profileCollection
            .document(userService.getUid().toString())
            .update("level.xp", newXp)
    }
}