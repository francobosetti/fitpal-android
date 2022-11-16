package com.example.fitpal_android.ui.screens.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.R
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
    var loginFormState by mutableStateOf(LoginFormState())
        private set

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when(event) {
            is LoginFormEvent.EmailChanged -> {
                loginFormState = loginFormState.copy(email = event.email)
            }
            is LoginFormEvent.PasswordChanged -> {
                loginFormState = loginFormState.copy(password = event.password)
            }
            is LoginFormEvent.Login -> {
                loginFormState = loginFormState.copy(loading = true)
                login()
                loginFormState = loginFormState.copy(loading = false)
            }
        }
    }

    // TODO: CHECK IN BACKEND
    private fun login() {
        // Remove trailing spaces
        loginFormState = loginFormState.copy(
            email = loginFormState.email.trimEnd(' ')
        )
        val emailResult = validateEmail.execute(loginFormState.email)

        loginFormState = loginFormState.copy(
            emailError = emailResult.errorMessage
        )

        val hasError = listOf(
            emailResult
        ).any { !it.successful}

        if(hasError) { return }

        viewModelScope.launch {

            try {
                userRepository.login(loginFormState.email, loginFormState.password)
                validationEventChannel.send(ValidationEvent.Success)
            } catch (e: Exception) {

                // TODO: HANDLE ERROR NO ENTIENDO COMO ANDA ESTO
                loginFormState = loginFormState.copy(
                    emailError = R.string.error_log_in
                    // TODO ver esto  xq nose como cambiar el e.message para que devuelva int
                )

            }

        }
    }
}