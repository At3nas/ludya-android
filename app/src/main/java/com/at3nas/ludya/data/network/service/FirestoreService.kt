package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreService @Inject constructor(
    private val firebase: FirebaseClient,
) {
    fun addDocumentToCollection(collection: String, docName: String, docData: Any) = runCatching {
        firebase.db
            .collection(collection)
            .document(docName)
            .set(docData)
    }

    suspend fun getDataFromDocument(collection: String, docName: String) {
        firebase.db
            .collection(collection)
            .document(docName)
            .get()
            .await()
    }
}