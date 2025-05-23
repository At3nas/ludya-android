package com.at3nas.ludya.presentation.home

import androidx.lifecycle.ViewModel
import com.at3nas.ludya.domain.usecase.auth.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentUser: GetCurrentUserUseCase
) :
    ViewModel() {
    fun getUsername(): String? {
        return currentUser.invoke()?.uid
    }
}