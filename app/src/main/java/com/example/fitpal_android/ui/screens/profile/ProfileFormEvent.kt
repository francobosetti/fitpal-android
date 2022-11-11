package com.example.fitpal_android.ui.screens.profile

sealed class ProfileFormEvent {
    data class FirstnameChanged(val firstname: String): ProfileFormEvent()
    data class LastnameChanged(val lastname: String): ProfileFormEvent()
    data class AvatarUrlChanged(val avatarUrl: String): ProfileFormEvent()

    object Submit: ProfileFormEvent()
}
