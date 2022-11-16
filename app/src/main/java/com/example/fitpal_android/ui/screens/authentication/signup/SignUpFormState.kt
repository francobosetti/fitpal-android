package com.example.fitpal_android.ui.screens.authentication.signup


data class SignUpFormState(
    val loading: Boolean = false,
    val firstname : String = "",
    val firstnameError : Int?  = null,
    val lastname : String = "",
    val lastnameError : Int?  = null,
    val email : String = "",
    val emailError : Int?  = null,
    val password : String = "",
    val passwordError : Int?  = null,
    val confirmPassword : String = "",
    val confirmPasswordError : Int?  = null,
    val serverError: String? = null,
)
