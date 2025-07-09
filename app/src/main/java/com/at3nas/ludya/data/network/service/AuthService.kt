package com.at3nas.ludya.data.network.service

import com.at3nas.ludya.data.network.client.FirebaseClient
import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.model.profile.Currency
import com.at3nas.ludya.domain.model.FirestoreCollections
import com.at3nas.ludya.domain.model.profile.Level
import com.at3nas.ludya.domain.model.profile.Profile
import com.at3nas.ludya.domain.model.user.User
import com.at3nas.ludya.domain.model.userLibrary.Library
import com.at3nas.ludya.presentation.logIn.model.Login
import com.at3nas.ludya.presentation.signUp.model.SignUp
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val firebase: FirebaseClient,
    private val firestoreService: FirestoreService
) {
    // User | Register //
    suspend fun signUpWithEmail(user: SignUp): AuthResponse {
        return try {
            val result =
                firebase.auth.createUserWithEmailAndPassword(
                    user.email, user.password
                ).await()

            val userData = User(
                email = result.user?.email,
                username = user.username,
                uuid = result.user?.uid,
                role = user.role
            )

            val userProfile = Profile(
                displayName = user.username,
                level = Level(),
                currency = Currency(),
            )

            val userLibrary = Library()

            result.user?.uid?.let {
                firestoreService.addDocumentToCollection(
                    collection = FirestoreCollections.COLLECTION_USER,
                    docName = it,
                    docData = userData
                )

                firestoreService.addDocumentToCollection(
                    collection = FirestoreCollections.COLLECTION_PROFILE,
                    docName = it,
                    docData = userProfile
                )

                firestoreService.addDocumentToCollection(
                    collection = FirestoreCollections.COLLECTION_LIBRARY,
                    docName = it,
                    docData = userLibrary
                )
            }
            AuthResponse.Success(result.user)
        } catch (e: Exception) {
            AuthResponse.Error(e)
        }
    }

    // User | Log In //
    suspend fun logInWithEmail(user: Login): AuthResponse {
        return try {
            val result = firebase.auth.signInWithEmailAndPassword(user.email, user.password).await()
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
}