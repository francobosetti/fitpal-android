package com.example.fitpal_android.data.remote

class DataSourceException(
    val code: Int,
    override val message: String,
    val details: List<String>?
) : Exception(message)