package com.example.fitpal_android.domain.use_case

class ValidateAvatarUrl {
    fun execute(avatarUrl: String) : ValidationResult {
        /*
        if(avatarUrl.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Url can't be blank"
            )
        }
        */ // IF BLANK -> default picture is loaded

        // TODO: check if url is valid
        /*
        if(!avatarUrl.validImageUrl) {
            return ValidationResult (
                succesful = false,
                errorMessage = "Invalid image url"
            )
        }
        */

        return ValidationResult(
            successful = true
        )
    }
}