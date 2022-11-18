package com.example.fitpal_android.ui.screens.appContent.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.remote.DataSourceException
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.domain.use_case.ApiCodeTranslator
import com.example.fitpal_android.domain.use_case.ValidateAvatarUrl
import com.example.fitpal_android.domain.use_case.ValidateFirstname
import com.example.fitpal_android.domain.use_case.ValidateLastname
import com.example.fitpal_android.ui.screens.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// TODO: update state when routing
class ProfileViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    var profileState by mutableStateOf(
        ProfileState(
            isFetching = false,
        )
    )
        private set

    var profileFormState by mutableStateOf(

        ProfileFormState(
            firstname = "",
            firstnameError = null,
            lastname = "",
            lastnameError = null,
            avatarUrl = "",
            avatarUrlError = null,
        )
    )
        private set

    init {
        updateUser()
    }

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: ProfileFormEvent) {
        when(event) {
            is ProfileFormEvent.FirstnameChanged -> {
                profileFormState = profileFormState.copy(firstname = event.firstname)
            }
            is ProfileFormEvent.LastnameChanged -> {
                profileFormState = profileFormState.copy(lastname = event.lastname)
            }
            is ProfileFormEvent.AvatarUrlChanged -> {
                profileFormState = profileFormState.copy(avatarUrl = event.avatarUrl)
            }
            is ProfileFormEvent.EditProfile -> {
                editProfile()
            }
        }
    }

    fun getCurrentUser() = viewModelScope.launch {
        profileState = profileState.copy(isFetching = true)
        try {
            val response = userRepository.getCurrentUser()
            // Should not be null
            response!!


            profileState = profileState.copy(
                firstname = response.firstname,
                lastname = response.lastname,
                email = response.email,
                avatarUrl = response.avatarUrl
            )


            profileFormState = profileFormState.copy(
                firstname = response.firstname,
                lastname = response.lastname,
                avatarUrl = response.avatarUrl
            )

        } catch (e : DataSourceException ) {
            // Handle the error and notify the UI when appropriate.
            profileState = profileState.copy(
                apiMsg = ApiCodeTranslator.translate(e.code)
            )
        }
        profileState = profileState.copy(isFetching = false)
    }

    fun updateUser() {
        if (!userRepository.isLoggedIn()) {
            return
        }

        viewModelScope.launch {
            profileState = profileState.copy(isFetching = true)
            try {
                val user = userRepository.getCurrentUser()

                // Shouldn't be null
                user!!

                profileState = profileState.copy(
                    firstname = user.firstname,
                    lastname = user.lastname,
                    email = user.email,
                    avatarUrl = user.avatarUrl,
                )

                profileFormState = profileFormState.copy(
                    firstname = user.firstname,
                    lastname = user.lastname,
                    avatarUrl = user.avatarUrl,
                )
            } catch (e: DataSourceException) {
                profileState = profileState.copy(
                    apiMsg = ApiCodeTranslator.translate(e.code)
                )
            }
            profileState = profileState.copy(isFetching = false)
        }
    }

    private fun editProfile() {
        // Remove trailing spaces
        profileFormState = profileFormState.copy(
            firstname = profileFormState.firstname.trimEnd(' '),
            lastname = profileFormState.lastname.trimEnd(' '),
            avatarUrl = profileFormState.avatarUrl.trimEnd(' '),
        )
        val firstnameResult = ValidateFirstname.execute(profileFormState.firstname)
        val lastnameResult = ValidateLastname.execute(profileFormState.lastname)
        val avatarUrlResult = ValidateAvatarUrl.execute(profileFormState.avatarUrl)

        profileFormState = profileFormState.copy(
            firstnameError = firstnameResult.errorMessage,
            lastnameError =  lastnameResult.errorMessage,
            avatarUrlError = avatarUrlResult.errorMessage,
        )

        val hasError = listOf(
            firstnameResult,
            lastnameResult,
            avatarUrlResult,
        ).any { !it.successful }

        if(hasError) { return }

        viewModelScope.launch {
            profileFormState = profileFormState.copy(editLoading = true)
            try {
                userRepository.updateUser(
                    firstname = profileFormState.firstname,
                    lastname = profileFormState.lastname,
                    avatarUrl = profileFormState.avatarUrl,
                )

                userRepository.fetchUser()
                val newUser = userRepository.getCurrentUser()
                // Should not be null
                newUser!!

                profileState = profileState.copy(
                    firstname = newUser.firstname,
                    lastname = newUser.lastname,
                    email = newUser.email,
                    avatarUrl = newUser.avatarUrl,
                )

                validationEventChannel.send(ValidationEvent.Success)
            } catch (e: DataSourceException) {
                profileState = profileState.copy(
                    apiMsg = ApiCodeTranslator.translate(e.code)
                )
            }
            profileFormState = profileFormState.copy(editLoading = false)
        }
    }

}