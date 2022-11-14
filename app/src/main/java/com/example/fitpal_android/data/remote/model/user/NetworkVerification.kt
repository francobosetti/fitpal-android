package com.example.fitpal_android.data.remote.model.user

import com.google.gson.annotations.SerializedName

class NetworkVerification (
    @SerializedName("email")
    var email: String,
    @SerializedName("code")
    var code: String
)