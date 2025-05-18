package com.at3nas.ludya.data.network

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Singleton


@Singleton
class FirebaseDatabaseClient() {
    val db: FirebaseFirestore
        get() = FirebaseFirestore.getInstance()
}