package com.example.fitpal_android.domain.use_case

import com.example.fitpal_android.R

object ValidateVerificationCode {
    fun execute(verificationCode: String) : ValidationResult{
        if(verificationCode.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.code_not_blank
            )
        }
        if(verificationCode.length != 6) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.code_6_long
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}