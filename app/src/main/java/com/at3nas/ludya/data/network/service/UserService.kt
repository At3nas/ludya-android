package com.at3nas.ludya.data.network.service


import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.domain.model.FirestoreCollections
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(
    private val firebase: FirebaseClient,
    private val firestoreService: FirestoreService
) {
    private suspend fun getUserData(): DocumentSnapshot? {
        val uid = firebase.auth.currentUser?.uid

        val userData = if (uid != null) {
            firebase.db
                .collection(FirestoreCollections.COLLECTION_USER)
                .document(uid)
                .get()
                .await()
        } else {
            null
        }
        return userData
    }

    suspend fun getEmail(): String? {
        return getUserData()?.getString("email")
    }

    suspend fun getRole(): String? {
        return getUserData()?.getString("role")
    }

    suspend fun getUsername(): String? {
        return getUserData()?.getString("username")
    }

    suspend fun getUid(): String? {
        return getUserData()?.getString("uuid")
    }
}