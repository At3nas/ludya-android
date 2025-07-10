package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.domain.model.FirestoreCollections
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.profile.Profile
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileService @Inject constructor(
    firebase: FirebaseClient,
    private val userService: UserService
) {
    private val profileCollection = firebase.db.collection(FirestoreCollections.COLLECTION_PROFILE)

    private suspend fun getProfileData(): DocumentSnapshot? {
        return profileCollection
            .document(userService.getUid().toString())
            .get()
            .await()
    }

    suspend fun getProfileById(userId: String): Profile? {
        return profileCollection
            .document(userId)
            .get()
            .await()
            .toObject(Profile::class.java)
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

    suspend fun getExp(): Long? {
        return getProfileData()?.getLong("level.currentExp")
    }

    suspend fun getTotalExp(): Long? {
        return getProfileData()?.getLong("level.totalExp")
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

    suspend fun updateExp(newExp: Double) {
        profileCollection
            .document(userService.getUid().toString())
            .update("level.currentExp", newExp)
    }

    suspend fun updateTotalExp(newExp: Double) {
        profileCollection
            .document(userService.getUid().toString())
            .update("level.totalExp", newExp)
    }
}