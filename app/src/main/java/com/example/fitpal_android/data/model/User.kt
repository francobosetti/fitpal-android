package com.example.fitpal_android.data.model

data class User (
    val id: ULong,
    val firstname: String,
    val lastname: String,
    var email: String,
    val avatarUrl: String,
) // TODO: check if Public user can be extended
