package com.example.fitpal_android.ui.screens.login

sealed class LoginFormEvent {
    data class EmailChanged(val email: String): LoginFormEvent()
    data class PasswordChanged(val password: String): LoginFormEvent()

    object Submit: LoginFormEvent()
}