package com.example.fitpal_android.domain.use_case

import android.util.Patterns

class ValidatePassword {
    fun execute(password: String) : ValidationResult {
        if(password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password cant be empty" // TODO: make spanish version
            )
        }
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password must contain at least 8 characters" // TODO: make spanish version
            )
        }
        val containsUpperAndLowerAndDigit = password.any { it.isDigit()} &&
                                            password.any { it.isLowerCase() } &&
                                            password.any { it.isUpperCase() }
        if(containsUpperAndLowerAndDigit) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password must contain at least a number an uppercase letter and a lowecase letter" // TODO: make spanish version
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}