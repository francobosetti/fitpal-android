package com.example.fitpal_android.domain.use_case

class ValidateLastname {
    fun execute(lastname: String) : ValidationResult {
        if(lastname.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Lastname can't be empty" // TODO: make spanish version
            )
        }
        val containsOnlyLetters = lastname.all { it.isLetter() }
        if(!containsOnlyLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = "Lastname must contain letters only" // TODO: make spanish version
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}