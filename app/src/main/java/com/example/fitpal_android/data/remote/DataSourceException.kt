package com.example.fitpal_android.data.remote

class DataSourceException(
    code: Int,
    message: String,
    details: List<String>?
) : Exception(message)