package com.at3nas.ludya.data.network

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Singleton


@Singleton
class FirebaseAuthClient() {
    val auth: FirebaseAuth
        get() = FirebaseAuth.getInstance()
}