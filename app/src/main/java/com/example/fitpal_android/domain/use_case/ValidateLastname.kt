package com.example.fitpal_android.domain.use_case

import com.example.fitpal_android.R

class ValidateLastname {
    fun execute(lastname: String) : ValidationResult {
        if(lastname.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.lastname_empty
            )
        }
        val containsOnlyLetters = lastname.all { it.isLetter() }
        if(!containsOnlyLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.lastName_leters
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}