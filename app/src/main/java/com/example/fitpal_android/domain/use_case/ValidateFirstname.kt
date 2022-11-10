package com.example.fitpal_android.domain.use_case

class ValidateFirstname {
    fun execute(firstname: String) : ValidationResult {
        if(firstname.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Firstname can't be empty" // TODO: make spanish version
            )
        }
        val containsOnlyLetters = firstname.all { it.isLetter() }
        if(!containsOnlyLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = "Firstname must contain letters only" // TODO: make spanish version
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}