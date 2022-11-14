package com.example.fitpal_android.ui.screens.authentication.login

data class LoginFormState(
    val loading: Boolean = false,
    val email: String = "",
    val emailError: String? = null,
    val password: String = ""
    //val passwordError: String? = null
)