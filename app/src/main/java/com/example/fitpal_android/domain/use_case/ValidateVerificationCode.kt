package com.example.fitpal_android.domain.use_case

class ValidateVerificationCode {
    fun execute(verificationCode: String) : ValidationResult{
        if(verificationCode.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Code can not be black"
            )
        }
        if(verificationCode.length != 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Code must be 6 digits long"
            )
        }
        val onlyDigits = !verificationCode.any { !it.isDigit() }
        if(!onlyDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Code must only contain digits"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}