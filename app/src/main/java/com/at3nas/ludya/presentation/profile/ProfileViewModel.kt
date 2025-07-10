package com.at3nas.ludya.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.at3nas.ludya.domain.model.course.Course
import com.at3nas.ludya.domain.model.profile.Profile
import com.at3nas.ludya.domain.repository.AccountDeletionRepository
import com.at3nas.ludya.domain.repository.ProfileRepository
import com.at3nas.ludya.domain.repository.UserRepository
import com.at3nas.ludya.domain.usecase.auth.GetCurrentUserUseCase
import com.at3nas.ludya.domain.usecase.auth.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository,
    private val accountDeletionRepository: AccountDeletionRepository,
    private val logOutUseCase: LogOutUseCase
) :
    ViewModel() {
    private val _username = MutableStateFlow<String>("User")
    val username: StateFlow<String>
        get() = _username

    private val _profile = MutableStateFlow<Profile>(Profile())
    val profile: StateFlow<Profile>
        get() = _profile

    // FUNCTIONS //
    fun loadProfileData() {
        viewModelScope.launch {
            val userId = userRepository.getUid()

            if (userId != null) {
                _profile.value = profileRepository.getProfileById(userId)!!
                _username.value = profileRepository.getUsername().toString()
            }
        }
    }

    // UPDATE //
    fun updateDisplayName(newName: String) {
        viewModelScope.launch {
            profileRepository.updateDisplayName(newName)
        }
    }

    // DELETE //
//    fun deleteAccount() {
//        accountDeletionRepository.deleteAccount()
//    }
//
//    fun logOut() {
//        viewModelScope.launch {
//            logOutUseCase.invoke()
//        }
//    }
}