package com.at3nas.ludya.presentation.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.repository.AuthRepository
import com.at3nas.ludya.presentation.logIn.model.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepo: AuthRepository
) :
    ViewModel() {

    fun logInWithEmail(user: Login, navigateToHome: () -> Unit) {
        viewModelScope.launch {
            val isSuccess =
                authRepo.logInWithEmail(user) is AuthResponse.Success

            if (isSuccess) {
                navigateToHome.invoke()
            }
        }
    }
}