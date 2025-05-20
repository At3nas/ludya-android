package com.at3nas.ludya.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.usecase.auth.SignUpWithEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) :
    ViewModel() {

    fun signUpWithEmail(email: String, password: String) {
        viewModelScope.launch {
            val isSignedUp = signUpWithEmailUseCase(email, password)
            if (isSignedUp) {
                print("Account created")
            } else {
                print("Error")
            }
        }
    }
}