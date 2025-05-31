package com.at3nas.ludya.data.network.service


import android.util.Log
import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(
    private val firebase: FirebaseClient
) {
    companion object {
        const val COLLECTION_USER = "users"
    }

    fun addUserToCollection(user: User) = runCatching {
        user.uuid?.let {
            firebase.db.collection(COLLECTION_USER)
                .document(it)
                .set(user)
        }
    }

    private suspend fun getUserDataFromCollection(): MutableMap<String, Any>? {
        val uid = firebase.auth.currentUser?.uid
        var userData: MutableMap<String, Any>? = null

        if (uid != null) {
            firebase.db.collection(COLLECTION_USER).document(uid)
                .get()
                .addOnSuccessListener { document ->
                    userData = document.data
                    Log.d("getUserData()|USER DATA: ", userData.toString())
                }
                .addOnFailureListener { exception ->
                    Log.e("ERROR", "the following exception occurred: ", exception)
                }
        }
        return userData
    }

    suspend fun getUsername(): Any? {
        val userData = getUserDataFromCollection()
        Log.d("getUsername()|USER DATA: ", userData.toString())

        val username = userData?.get("username")
        Log.d("getUsername()|USERNAME: ", username.toString())

        return username
    }
}