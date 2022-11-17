package com.example.fitpal_android.ui.screens.authentication.verify

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.R
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.domain.use_case.ValidateVerificationCode
import com.example.fitpal_android.ui.screens.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class VerifyViewModel(
    private val validateVerificationCode: ValidateVerificationCode = ValidateVerificationCode(),
    private val userRepository: UserRepository
) : ViewModel() {

    var verifyFormState by mutableStateOf(VerifyFromState())
        private set

    private var validationEventChannel = Channel<ValidationEvent>()
    var validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: VerifyFormEvent, email: String? = null, password: String? = null) {
        when (event) {
            is VerifyFormEvent.CodeChanged -> {
                verifyFormState = verifyFormState.copy(verificationCode = event.code)
            }

            is VerifyFormEvent.DismissMessage -> {
                verifyFormState = verifyFormState.copy(apiMsg = null)
            }

            is VerifyFormEvent.ResendCode -> {
                if (email != null) {
                    resendCode(email)
                }
            }

            is VerifyFormEvent.VerifyCode -> {
                if (email != null && password != null) {
                    verifyCode(email, password)
                }
            }
        }
    }


    private fun verifyCode(email: String, password: String) {
        val verificationCodeResult =
            validateVerificationCode.execute(verifyFormState.verificationCode)

        verifyFormState = verifyFormState.copy(
            verificationCodeError = verificationCodeResult.errorMessage,
        )

        val hasError = listOf(
            verificationCodeResult,
        ).any { !it.successful }

        if (hasError) {
            return
        }

        viewModelScope.launch {
            verifyFormState = verifyFormState.copy(verifyLoading = true)
            try {
                userRepository.verifyEmail(email, verifyFormState.verificationCode)
                userRepository.login(email, password)
                validationEventChannel.send(ValidationEvent.Success)
            } catch (e: Exception) {
                verifyFormState = verifyFormState.copy(
                    verificationCodeError = R.string.error_verify_mail
                    // TODO ver esto  xq nose como cambiar el e.message para que devuelva int
                )
            }
            verifyFormState = verifyFormState.copy(verifyLoading = false)
        }
    }

    private fun resendCode(email: String) {
        viewModelScope.launch {
            verifyFormState = verifyFormState.copy(resendLoading = true)
            try {
                userRepository.resendVerification(email)
                verifyFormState = verifyFormState.copy(
                    apiMsg = R.string.verif_sent,
                )
            } catch (e: Exception) {
                verifyFormState = verifyFormState.copy(
                    apiMsg = R.string.error_resend_verif
                    // TODO ver esto  xq nose como cambiar el e.message para que devuelva int
                )
            }
            verifyFormState = verifyFormState.copy(resendLoading = false)
        }
    }
}