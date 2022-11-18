package com.example.fitpal_android.domain.use_case

import com.example.fitpal_android.R

object ValidatePassword {
    fun execute(password: String) : ValidationResult {
        if(password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.pass_empty
            )
        }
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.pass_8_char
            )
        }
        val containsUpperAndLowerAndDigit = password.any { it.isDigit()} &&
                                            password.any { it.isLowerCase() } &&
                                            password.any { it.isUpperCase() }
        if(!containsUpperAndLowerAndDigit) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.pass_parameters
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}