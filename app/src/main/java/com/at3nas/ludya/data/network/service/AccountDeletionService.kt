package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.domain.model.FirestoreCollections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountDeletionService @Inject constructor(
    val firebase: FirebaseClient
) {
    private val db = firebase.db
    private val currentUser = firebase.auth.currentUser

    private val collections = listOf(
        FirestoreCollections.COLLECTION_USER,
        FirestoreCollections.COLLECTION_PROFILE,
        FirestoreCollections.COLLECTION_LIBRARY,
        FirestoreCollections.COLLECTION_COURSE_PROGRESS
    )

    fun deleteAccount() {
        if (currentUser != null) {
            collections.forEach { collection ->
                db
                    .collection(collection)
                    .document(currentUser.uid)
                    .delete()
            }
            currentUser.delete()
        }
    }
}