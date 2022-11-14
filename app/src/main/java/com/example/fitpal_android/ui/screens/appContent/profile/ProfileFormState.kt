package com.example.fitpal_android.ui.screens.appContent.profile

data class ProfileFormState(
    val editLoading: Boolean = false,
    val firstname: String,
    val firstnameError: String? = null,
    val lastname: String,
    val lastnameError: String? = null,
    val avatarUrl: String,
    val avatarUrlError: String? = null,
)