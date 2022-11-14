package com.example.fitpal_android.ui.screens.authentication.verify

sealed class VerifyFormEvent {
    data class CodeChanged(val code : String) : VerifyFormEvent()

    object ResendCode : VerifyFormEvent()
    object VerifyCode : VerifyFormEvent()
}
