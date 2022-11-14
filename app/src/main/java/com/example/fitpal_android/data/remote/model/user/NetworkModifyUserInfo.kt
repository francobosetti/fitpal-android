package com.example.fitpal_android.data.remote.model.user

import com.google.gson.annotations.SerializedName


data class NetworkModifyUserInfo(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String
)