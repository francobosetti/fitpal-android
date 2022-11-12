package com.example.fitpal_android.ui.screens.signup


data class SignUpFormState(
    val firstname : String = "",
    val firstnameError : String?  = null,
    val lastname : String = "",
    val lastnameError : String?  = null,
    val email : String = "",
    val emailError : String?  = null,
    val password : String = "",
    val passwordError : String?  = null,
    val confirmPassword : String = "",
    val confirmPasswordError : String?  = null
)
