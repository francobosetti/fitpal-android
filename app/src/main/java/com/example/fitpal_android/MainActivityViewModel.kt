package com.example.fitpal_android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.UserRepository

class MainActivityViewModel(
    userRepository: UserRepository,
) : ViewModel() {
    var state by mutableStateOf(MainActivityState(isAuthenticated = userRepository.isLoggedIn()))
        private set

    fun loggedIn() {
        state = state.copy(isAuthenticated = true)
    }

    fun loggedOut() {
        state = state.copy(isAuthenticated = false)
    }
}