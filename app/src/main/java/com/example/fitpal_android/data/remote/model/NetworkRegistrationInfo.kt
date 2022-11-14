package com.example.fitpal_android.data.remote.model

import com.google.gson.annotations.SerializedName

class NetworkRegistrationInfo(
    @SerializedName("password")
    var password: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("username")
    var username: String = email,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
)