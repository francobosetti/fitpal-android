package com.example.fitpal_android.ui.screens.authentication.login

sealed class LoginFormEvent {
    data class EmailChanged(val email: String): LoginFormEvent()
    data class PasswordChanged(val password: String): LoginFormEvent()

    object DismissMessage: LoginFormEvent()
    object Login: LoginFormEvent()
}