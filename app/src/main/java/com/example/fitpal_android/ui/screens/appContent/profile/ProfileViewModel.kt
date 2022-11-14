package com.example.fitpal_android.ui.screens.appContent.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.DEPRECATED.UserRepository
import com.example.fitpal_android.domain.use_case.ValidateAvatarUrl
import com.example.fitpal_android.domain.use_case.ValidateFirstname
import com.example.fitpal_android.domain.use_case.ValidateLastname
import com.example.fitpal_android.ui.screens.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// TODO: update state when routing
class ProfileViewModel(private val validateFirstname: ValidateFirstname = ValidateFirstname(),
                       private val validateLastname: ValidateLastname = ValidateLastname(),
                       private val validateAvatarUrl: ValidateAvatarUrl = ValidateAvatarUrl()
) : ViewModel() {
    private var currentUser = UserRepository().getCurrentUser()
    var profileState by mutableStateOf(
        ProfileState(
            firstname = currentUser.firstname,
            lastname = currentUser.lastname,
            email = currentUser.email,
            avatarUrl = currentUser.avatarUrl!!
        )
    )
    var profileFormState by mutableStateOf(
        ProfileFormState(
            firstname = currentUser.firstname,
            lastname = currentUser.lastname,
            avatarUrl = currentUser.avatarUrl!!
        )
    )

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

    // TODO: CHECK IN BACKEND
    private fun editProfile() {
        val firstnameResult = validateFirstname.execute(profileFormState.firstname)
        val lastnameResult = validateLastname.execute(profileFormState.lastname)
        val avatarUrlResult = validateAvatarUrl.execute(profileFormState.avatarUrl)

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
            profileState = profileState.copy(
                firstname = profileFormState.firstname,
                lastname = profileFormState.lastname,
                avatarUrl = profileFormState.avatarUrl,
            )
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

}