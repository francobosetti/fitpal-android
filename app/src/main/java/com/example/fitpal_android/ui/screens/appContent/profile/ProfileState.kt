package com.example.fitpal_android.ui.screens.appContent.profile

data class ProfileState(

    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val avatarUrl: String = "",

    val isFetching: Boolean = false,
    val apiMsg: Int? = null
)