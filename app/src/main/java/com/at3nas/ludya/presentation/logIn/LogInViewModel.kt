package com.at3nas.ludya.presentation.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.model.ExistingUser
import com.at3nas.ludya.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepo: AuthRepository
) :
    ViewModel() {

    fun logInWithEmail(user: ExistingUser, navigateToHome: () -> Unit) {
        viewModelScope.launch {
            val isSuccess =
                authRepo.logInWithEmail(user.email, user.password) is AuthResponse.Success

            if (isSuccess) {
                navigateToHome.invoke()
            }
        }
    }
}