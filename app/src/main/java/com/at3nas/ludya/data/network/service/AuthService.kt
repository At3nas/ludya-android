package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.model.NewUser
import com.at3nas.ludya.domain.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val firebase: FirebaseClient,
    private val userService: UserService
) {
    //private val _authState = MutableLiveData<AuthState>()
    //val authState: LiveData<AuthState> = _authState

    // User | Register //
    suspend fun signUpWithEmail(user: NewUser): AuthResponse {
        return try {
            val result = firebase.auth.createUserWithEmailAndPassword(user.email, user.password).await()
            userService.addUserToCollection(
                User(
                    email = result.user?.email,
                    username = user.username,
                    uuid = result.user?.uid
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