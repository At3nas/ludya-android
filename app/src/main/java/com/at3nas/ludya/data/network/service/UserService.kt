package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseDatabaseClient
import com.at3nas.ludya.domain.model.User
import javax.inject.Inject

class UserService @Inject constructor(private val firebase: FirebaseDatabaseClient) {
    companion object {
        const val COLLECTION_USER = "users"
    }

    suspend fun addUserToCollection(user: User) = runCatching {
//        val newUser = User(
//            user.uuid,
//            user.email,
//            user.isTeacher
//        )

        user.uuid?.let {
            firebase.db.collection(COLLECTION_USER)
                .document(it)
                .set(user)
        }
    }

}