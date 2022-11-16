package com.example.fitpal_android.domain.use_case

import com.example.fitpal_android.R

class ValidateConfirmPassword {
    fun execute(password: String, confirmPassword: String) : ValidationResult {
        if(password != confirmPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.pass_notMatch
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}