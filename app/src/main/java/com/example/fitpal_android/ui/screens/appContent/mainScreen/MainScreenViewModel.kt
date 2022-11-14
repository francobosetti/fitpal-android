package com.example.fitpal_android.ui.screens.appContent.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var mainScreenState by mutableStateOf(
        MainScreenState(
            isFetching = false,
            avatarUrl = ""
        )
    )
        private set

    init {
        viewModelScope.launch {
            mainScreenState = mainScreenState.copy(isFetching = true)
            val user = userRepository.getCurrentUser()
            // Shouldn't be null
            user!!

            mainScreenState = mainScreenState.copy(
                avatarUrl = user.avatarUrl,
                isFetching = false,
            )
        }
    }

    suspend fun logout() {
        userRepository.logout()
    }
    
    suspend fun updateAvatarUrl() {
        viewModelScope.launch {
            mainScreenState = mainScreenState.copy(isFetching = true)
            userRepository.fetchUser()
            val user = userRepository.getCurrentUser()
            // Shouldn't be null
            user!!

            mainScreenState = mainScreenState.copy(
                avatarUrl = user.avatarUrl,
                isFetching = false,
            )
        }
    }


}