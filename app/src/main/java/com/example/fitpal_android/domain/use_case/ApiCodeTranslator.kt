package com.example.fitpal_android.domain.use_case

import com.example.fitpal_android.R
import com.example.fitpal_android.data.remote.ApiErrorCodes

object ApiCodeTranslator {
    fun translate(code: Int) : Int {
        when(code) {
            ApiErrorCodes.INV_DATA -> {
                return R.string.invalid_data
            }
            ApiErrorCodes.EMAIL_ALREADY_IN_USE -> {
                return R.string.email_already_in_use
            }
            ApiErrorCodes.EMAIL_NOT_FOUND_ERROR -> {
                return R.string.email_not_found
            }
            ApiErrorCodes.INV_EMAIL_OR_PASSWORD -> {
                return R.string.inv_email_or_pass
            }
            ApiErrorCodes.DATABASE_ERROR -> {
                return R.string.database_error
            }
            ApiErrorCodes.INVALID_REQUEST -> {
                return R.string.invalid_request
            }
            ApiErrorCodes.UNAUTHORIZED -> {
                return R.string.unauthorized
            }
            ApiErrorCodes.INV_VERIFICATION -> {
                return R.string.inv_verification_code
            }
            ApiErrorCodes.CONNECTION_ERROR -> {
                return R.string.connection_err
            }
            ApiErrorCodes.UNEXPECTED_ERROR -> {
                return R.string.unexpected_err
            }
            ApiErrorCodes.MISSING_ERROR -> {
                return R.string.unknown_err
            }

        }
        return R.string.unknown_err
    }
}