package com.at3nas.ludya.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val firebase: FirebaseAuthClient
) {
    //private val _authState = MutableLiveData<AuthState>()
    //val authState: LiveData<AuthState> = _authState

    // User | Register //
    suspend fun signUpWithEmail(email: String, password: String): AuthResult? {
        return firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    // User | Log In //
    suspend fun logInWithEmail(email: String, password: String): AuthResult? {
        return firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    // User | Log Out //
    fun logOut() {
        firebase.auth.signOut()
        //_authState.value = AuthState.Unauthenticated
    }

    // User | Get //
    fun getCurrentUser(): FirebaseUser? {
        return firebase.auth.currentUser
    }

    // Auth | Check status //
//    fun checkAuthStatus() {
//        if (this.getCurrentUser() != null) {
//            _authState.value = AuthState.Authenticated
//        } else {
//            _authState.value = AuthState.Unauthenticated
//        }
//    }
}