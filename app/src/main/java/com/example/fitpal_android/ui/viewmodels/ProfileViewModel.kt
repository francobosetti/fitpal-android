package com.example.fitpal_android.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.domain.use_case.ValidateAvatarUrl
import com.example.fitpal_android.domain.use_case.ValidateFirstname
import com.example.fitpal_android.domain.use_case.ValidateLastname
import com.example.fitpal_android.ui.events.ProfileFormEvent
import com.example.fitpal_android.ui.states.ProfileFormState
import com.example.fitpal_android.ui.states.ProfileState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val validateFirstname: ValidateFirstname = ValidateFirstname(),
                       private val validateLastname: ValidateLastname = ValidateLastname(),
                       private val validateAvatarUrl: ValidateAvatarUrl = ValidateAvatarUrl()
) : ViewModel() {
    var currentUser = UserRepository().getCurrentUser()
    var profileState by mutableStateOf(ProfileState(
        firstname = currentUser.firstname,
        lastname = currentUser.lastname,
        email = currentUser.email,
        avatarUrl = currentUser.avatarUrl
    )
    )
    var formState by mutableStateOf(ProfileFormState(
        firstname = currentUser.firstname,
        lastname = currentUser.lastname,
        avatarUrl = currentUser.avatarUrl
    ))

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: ProfileFormEvent) {
        when(event) {
            is ProfileFormEvent.FirstnameChanged -> {
                formState = formState.copy(firstname = event.firstname)
            }
            is ProfileFormEvent.LastnameChanged -> {
                formState = formState.copy(lastname = event.lastname)
            }
            is ProfileFormEvent.AvatarUrlChanged -> {
                formState = formState.copy(avatarUrl = event.avatarUrl)
            }
            is ProfileFormEvent.Submit -> {
                submitForm()
            }
        }
    }

    // TODO: CHECK IN BACKEND
    private fun submitForm() {
        val firstnameResult = validateFirstname.execute(formState.firstname)
        val lastnameResult = validateLastname.execute(formState.lastname)
        val avatarUrlResult = validateAvatarUrl.execute(formState.avatarUrl)

        formState = formState.copy(
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
            profileState = profileState.copy(
                firstname = formState.firstname,
                lastname = formState.lastname,
                avatarUrl = formState.avatarUrl,
            )
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}