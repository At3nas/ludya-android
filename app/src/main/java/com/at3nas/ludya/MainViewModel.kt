package com.at3nas.ludya

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var currentUser by mutableStateOf<FirebaseUser?>(null)
        private set

    var isUserAuthenticated by mutableStateOf(false)
        private set

    init {
        getCurrentUser()
        checkCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            currentUser = authRepository.getCurrentUser()
        }
    }

    private fun checkCurrentUser() {
        viewModelScope.launch {
            isUserAuthenticated = currentUser != null
        }
    }
}