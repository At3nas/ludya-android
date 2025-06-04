package com.at3nas.ludya.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.data.network.response.AuthResponse
import com.at3nas.ludya.domain.repository.AuthRepository
import com.at3nas.ludya.presentation.signUp.model.SignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepo: AuthRepository
) :
    ViewModel() {
    fun signUpWithEmail(user: SignUp, navigateToHome: () -> Unit) {
        viewModelScope.launch {
            if (validatePasswordMatch(user.password, user.confirmPassword)) {
                val isSuccess = authRepo.signUpWithEmail(user) is AuthResponse.Success

                if (isSuccess) {
                    navigateToHome.invoke()
                }
            }
        }
    }

    // Validates if passwords match each other
    private fun validatePasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}