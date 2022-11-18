package com.example.fitpal_android.data.remote

object ApiErrorCodes {
    const val INV_DATA = 1 // TODO: REVISAR, no deberia ser necesario con nuestros chequeos
    const val EMAIL_ALREADY_IN_USE = 2
    const val EMAIL_NOT_FOUND_ERROR = 3
    const val INV_EMAIL_OR_PASSWORD = 4
    const val DATABASE_ERROR = 5        //TODO:REVISAR
    const val INVALID_REQUEST = 6       //TODO:REVISAR
    const val UNAUTHORIZED = 7          //TODO:MANEJAR EN EL LOGIN DEL MAIN
    const val INV_VERIFICATION = 8
    const val CONNECTION_ERROR = 98
    const val UNEXPECTED_ERROR = 99
    const val MISSING_ERROR = 1000 //TODO: poner un valor real
}