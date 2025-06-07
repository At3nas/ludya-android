package com.at3nas.ludya.presentation.mainScaffold

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.at3nas.ludya.domain.model.user.UserRole
import com.at3nas.ludya.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScaffoldViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var isUserTeacher by mutableStateOf<Boolean>(false)
        private set

    init {
        viewModelScope.launch {
            isUserTeacher = isUserTeacher()
        }
    }

    private suspend fun isUserTeacher(): Boolean {
        val userRole = userRepository.getRole()
        return userRole == UserRole.TEACHER.toString()
    }
}