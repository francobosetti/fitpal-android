package com.example.fitpal_android.domain.use_case

import com.example.fitpal_android.R

class ValidateFirstname {
    fun execute(firstname: String) : ValidationResult {
        if(firstname.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.firstName_empty
            )
        }
        val containsOnlyLetters = firstname.all { it.isLetter() }
        if(!containsOnlyLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = R.string.firstName_leters
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}