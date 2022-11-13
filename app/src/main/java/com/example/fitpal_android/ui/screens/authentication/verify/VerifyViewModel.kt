package com.example.fitpal_android.ui.screens.authentication.verify

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.domain.use_case.ValidateVerificationCode
import com.example.fitpal_android.ui.screens.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class VerifyViewModel(
    private val validateVerificationCode : ValidateVerificationCode = ValidateVerificationCode()
) : ViewModel() {

    var verfyFormState by mutableStateOf(VerifyFromState())
        private set

    var validationEventChannel = Channel<ValidationEvent>()
    var validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: VerifyFormEvent) {
        when (event) {
            is VerifyFormEvent.CodeChanged -> {
                verfyFormState = verfyFormState.copy(verificationCode = event.code)
            }

            is VerifyFormEvent.ResendCode -> {
                resendCode()
            }

            is VerifyFormEvent.Submit -> {
                submitForm()
            }
        }
    }

    // TODO: ADD BACKEND
    private fun submitForm() {
        val verificationCodeResult = validateVerificationCode.execute(verfyFormState.verificationCode)

        verfyFormState = verfyFormState.copy(
            verificationCodeError = verificationCodeResult.errorMessage,
        )

        val hasError = listOf(
            verificationCodeResult,
        ).any { !it.successful }

        if (hasError) {
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    // TODO: ADD BACKEND
    private fun resendCode() {
        return
    }
}
