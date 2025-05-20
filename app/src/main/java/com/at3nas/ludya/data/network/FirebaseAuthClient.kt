package com.at3nas.ludya.data.network

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseAuthClient @Inject constructor() {
    val auth: FirebaseAuth
        get() = FirebaseAuth.getInstance()
}