package com.example.fitpal_android.data.remote.model.user

import com.google.gson.annotations.SerializedName

data class NetworkCredentials (

    @SerializedName("username")
    var username : String,
    @SerializedName("password")
    var password : String
)
