package com.example.fitpal_android.ui.screens.authentication.verify

data class VerifyFromState(
    val verifyLoading: Boolean = false,
    val resendLoading: Boolean = false,
    val verificationCode: String = "",
    val verificationCodeError: Int? = null
)
