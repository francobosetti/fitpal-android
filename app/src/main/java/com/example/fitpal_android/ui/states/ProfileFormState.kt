package com.example.fitpal_android.ui.states

data class ProfileFormState(
    val firstname: String,
    val firstnameError: String? = null,
    val lastname: String,
    val lastnameError: String? = null,
    val avatarUrl: String,
    val avatarUrlError: String? = null,
)