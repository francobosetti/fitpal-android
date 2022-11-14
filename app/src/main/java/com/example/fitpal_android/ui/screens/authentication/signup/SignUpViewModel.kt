package com.example.fitpal_android.ui.screens.authentication.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.domain.use_case.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val validateFirstname: ValidateFirstname = ValidateFirstname(),
    private val validateLastname: ValidateLastname = ValidateLastname(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateConfirmPassword: ValidateConfirmPassword = ValidateConfirmPassword(),
    private val userRepository: UserRepository
) : ViewModel() {
    var signUpFormState by mutableStateOf(SignUpFormState())
        private set

    private val validationEventChannel = Channel<SignUpValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event : SignUpFormEvent) {
        when(event) {
            is SignUpFormEvent.FirstnameChanged -> {
                signUpFormState = signUpFormState.copy(firstname = event.firstname)
            }
            is SignUpFormEvent.LastnameChanged -> {
                signUpFormState = signUpFormState.copy(lastname = event.lastname)
            }
            is SignUpFormEvent.EmailChanged -> {
                signUpFormState = signUpFormState.copy(email = event.email)
            }
            is SignUpFormEvent.PasswordChanged -> {
                signUpFormState = signUpFormState.copy(password = event.password)
            }
            is SignUpFormEvent.ConfirmPasswordChanged -> {
                signUpFormState = signUpFormState.copy(confirmPassword = event.confirmPassword)
            }
            is SignUpFormEvent.SignUp -> {
                signUpFormState = signUpFormState.copy(loading = true)
                signUp()
                signUpFormState = signUpFormState.copy(loading = false)
            }
        }
    }

    private fun signUp() {
        // Remove trailing spaces
        signUpFormState = signUpFormState.copy(
            firstname = signUpFormState.firstname.trimEnd(' '),
            lastname = signUpFormState.lastname.trimEnd(' '),
            email = signUpFormState.email.trimEnd(' ')
        )
        val firstnameResult = validateFirstname.execute(signUpFormState.firstname)
        val lastnameResult = validateLastname.execute(signUpFormState.lastname)
        val emailResult = validateEmail.execute(signUpFormState.email)
        val passwordResult = validatePassword.execute(signUpFormState.password)
        val confirmPasswordResult = validateConfirmPassword.execute(signUpFormState.password, signUpFormState.confirmPassword)

        signUpFormState = signUpFormState.copy(
            firstnameError = firstnameResult.errorMessage,
            lastnameError =  lastnameResult.errorMessage,
            emailError =  emailResult.errorMessage,
            passwordError =  passwordResult.errorMessage,
            confirmPasswordError =  confirmPasswordResult.errorMessage,
        )

        val hasError = listOf(
            firstnameResult,
            lastnameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.successful }

        if(hasError) { return }

        viewModelScope.launch {
            try {
                userRepository.registerUser(
                    firstname = signUpFormState.firstname,
                    lastname = signUpFormState.lastname,
                    email = signUpFormState.email,
                    password = signUpFormState.password
                )
                validationEventChannel.send(SignUpValidationEvent.Success(signUpFormState.email, signUpFormState.password))
            } catch (e: Exception) {
                signUpFormState = signUpFormState.copy(
                    firstnameError = null,
                    lastnameError = null,
                    emailError = null,
                    passwordError = null,
                    confirmPasswordError = null,
                    serverError = e.message
                )
            }

        }
    }
}