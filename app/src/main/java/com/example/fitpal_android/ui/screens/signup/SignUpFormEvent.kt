package com.example.fitpal_android.ui.screens.signup

sealed class SignUpFormEvent {
    data class FirstnameChanged(val firstname: String): SignUpFormEvent()
    data class LastnameChanged(val lastname: String): SignUpFormEvent()
    data class EmailChanged(val email: String): SignUpFormEvent()
    data class PasswordChanged(val password: String): SignUpFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String): SignUpFormEvent()

    object Submit: SignUpFormEvent()
}
