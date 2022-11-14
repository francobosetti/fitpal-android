package com.example.fitpal_android.ui.screens.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.domain.use_case.ValidateEmail
import com.example.fitpal_android.ui.screens.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val userRepository: UserRepository
) : ViewModel() {
    var formState by mutableStateOf(LoginFormState())
        private set

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when(event) {
            is LoginFormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }
            is LoginFormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }
            is LoginFormEvent.Submit -> {
                submitForm()
            }
        }
    }

    // TODO: CHECK IN BACKEND
    private fun submitForm() {
        val emailResult = validateEmail.execute(formState.email)

        formState = formState.copy(
            emailError = emailResult.errorMessage
        )

        val hasError = listOf(
            emailResult
        ).any { !it.successful}

        if(hasError) { return }

        viewModelScope.launch {

            try {
                userRepository.login(formState.email, formState.password)
                validationEventChannel.send(ValidationEvent.Success)
            } catch (e: Exception) {

                // TODO: HANDLE ERROR NO ENTIENDO COMO ANDA ESTO
                formState = formState.copy(
                    emailError = e.message
                )

            }

        }
    }
}