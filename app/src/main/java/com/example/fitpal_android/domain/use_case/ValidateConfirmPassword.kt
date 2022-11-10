package com.example.fitpal_android.domain.use_case

class ValidateConfirmPassword {
    fun execute(password: String, confirmPassword: String) : ValidationResult {
        if(password != confirmPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords do not match" // TODO: make spanish version
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}