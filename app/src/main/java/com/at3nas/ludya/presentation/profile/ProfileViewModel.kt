package com.at3nas.ludya.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) :
    ViewModel() {
    // VARIABLES //
    var username by mutableStateOf<String?>(null)
        private set

    var displayName by mutableStateOf<String?>(null)
        private set

    var coins by mutableStateOf<Long?>(0)
        private set

    var gems by mutableStateOf<Long?>(0)
        private set

    var level by mutableStateOf<Long?>(1)
        private set

    var exp by mutableStateOf<Long?>(10)
        private set

    // FUNCTIONS //
    init {
        loadProfileData()
    }

    private fun loadProfileData() {
        viewModelScope.launch {
            username = profileRepository.getUsername()
            displayName = profileRepository.getDisplayName()
            coins = profileRepository.getCoins()
            gems = profileRepository.getGems()
            level = profileRepository.getLevel()
            exp = profileRepository.getExp()
        }
    }

    // UPDATE //
    fun updateDisplayName() {
        viewModelScope.launch {
            val newName = "Atenas Student"
            profileRepository.updateDisplayName(newName)
            displayName = newName
        }
    }
}