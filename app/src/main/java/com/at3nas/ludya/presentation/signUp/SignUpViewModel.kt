package com.at3nas.ludya.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.model.NewUser
import com.at3nas.ludya.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepo: AuthRepository
) :
    ViewModel() {
    fun signUpWithEmail(user: NewUser) {
        viewModelScope.launch {
            if (validatePasswordMatch(user.password, user.confirmPassword)) {
                authRepo.signUpWithEmail(user)
            }
        }
    }

    // Validates if passwords match each other //
    private fun validatePasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}