package com.at3nas.ludya.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var username by mutableStateOf<String?>("user")

    init {
        updateUsername()
    }

    private fun setUsernameFromData() {
        viewModelScope.launch {
            username = userRepo.getUsername()
        }
    }

    private fun updateUsername(): String? {
        setUsernameFromData()
        return username
    }

}