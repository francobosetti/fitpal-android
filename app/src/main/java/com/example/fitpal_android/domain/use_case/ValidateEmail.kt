package com.example.fitpal_android.domain.use_case

import android.util.Patterns

class ValidateEmail {
    fun execute(email: String) : ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank" // TODO: make spanish version
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid email" // TODO: make spanish version
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}