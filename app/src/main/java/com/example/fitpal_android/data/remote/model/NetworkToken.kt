package com.example.fitpal_android.data.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkToken (

    @SerializedName("token")
    var token : String
)