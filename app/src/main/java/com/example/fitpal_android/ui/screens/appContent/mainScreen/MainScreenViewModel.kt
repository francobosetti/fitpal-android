package com.example.fitpal_android.ui.screens.appContent.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.util.resetRepositories
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.internal.concurrent.Task

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
        updateAvatarUrl()
    }

    suspend fun logout() {
        userRepository.logout()
        resetRepositories()
    }



    fun updateAvatarUrl() {
        if (!userRepository.isLoggedIn()) {
            return
        }

        viewModelScope.launch {
            try{
                mainScreenState = mainScreenState.copy(isFetching = true)
                val user = userRepository.getCurrentUser()
                // Shouldn't be null
                user!!

                mainScreenState = mainScreenState.copy(
                    avatarUrl = user.avatarUrl,
                    isFetching = false,
                )
            } catch (e : Exception) {
                //TODO: do smth
            }
            mainScreenState = mainScreenState.copy(isFetching = false)
        }
    }


}