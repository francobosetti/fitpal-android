package com.example.fitpal_android.domain.use_case

import android.util.Patterns
import com.example.fitpal_android.R

class ValidateEmail {
    fun execute(email: String) : ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.email_empty
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.email_format
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}