package com.example.fitpal_android.ui.screens.appContent.profile

data class ProfileFormState(
    val editLoading: Boolean = false,
    val firstname: String,
    val firstnameError: Int? = null,
    val lastname: String,
    val lastnameError: Int? = null,
    val avatarUrl: String,
    val avatarUrlError: Int? = null,
)