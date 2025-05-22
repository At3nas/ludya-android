package com.at3nas.ludya.data.network.client

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseAuthClient @Inject constructor() {
    val auth: FirebaseAuth
        get() = FirebaseAuth.getInstance()
}