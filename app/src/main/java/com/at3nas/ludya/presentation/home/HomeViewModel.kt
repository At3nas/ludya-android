package com.at3nas.ludya.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepo: UserRepository
) :
    ViewModel() {
    fun getUsername(): Any? {
        var username: Any? = null

        viewModelScope.launch {
            username = userRepo.getUsername()
        }
        return username
    }
}