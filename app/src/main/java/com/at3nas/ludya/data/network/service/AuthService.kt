package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseAuthClient
import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.model.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val firebase: FirebaseAuthClient,
    private val userService: UserService
) {
    //private val _authState = MutableLiveData<AuthState>()
    //val authState: LiveData<AuthState> = _authState

    // User | Register //
    suspend fun signUpWithEmail(email: String, password: String): AuthResponse {
        return try {
            val result = firebase.auth.createUserWithEmailAndPassword(email, password).await()
            userService.addUserToCollection(
                User(
                    email = result.user?.email,
                    uuid = result.user?.uid,
                    isTeacher = false
                )
            )
            AuthResponse.Success(result.user)
        } catch (e: Exception) {
            AuthResponse.Error(e)
        }
    }

    // User | Log In //
    suspend fun logInWithEmail(email: String, password: String): AuthResponse {
        return try {
            val result = firebase.auth.signInWithEmailAndPassword(email, password).await()
            AuthResponse.Success(result.user)
        } catch (e: Exception) {
            AuthResponse.Error(e)
        }
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