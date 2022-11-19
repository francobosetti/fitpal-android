package com.example.fitpal_android.data.remote.model.exercise

import com.google.gson.annotations.SerializedName

class NetworkExerciseImage(
    @SerializedName("id")
    var id: Int,
    @SerializedName("number")
    var number: Int,
    @SerializedName("url")
    var url: String,
)