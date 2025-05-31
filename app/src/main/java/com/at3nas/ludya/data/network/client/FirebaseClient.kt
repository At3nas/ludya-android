package com.at3nas.ludya.data.network.client

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseClient @Inject constructor() {
    val auth: FirebaseAuth
        get() = FirebaseAuth.getInstance()

    val db: FirebaseFirestore
        get() = FirebaseFirestore.getInstance()
}