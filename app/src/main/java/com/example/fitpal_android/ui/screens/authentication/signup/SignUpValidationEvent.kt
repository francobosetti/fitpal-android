package com.example.fitpal_android.ui.screens.authentication.signup

sealed class SignUpValidationEvent(
    val email : String = "",
    val password: String = "",
) {
    class Success(email :String, password: String): SignUpValidationEvent(email, password)
}