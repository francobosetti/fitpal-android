package com.example.fitpal_android.ui.screens.login

data class LoginFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = ""
    //val passwordError: String? = null
)