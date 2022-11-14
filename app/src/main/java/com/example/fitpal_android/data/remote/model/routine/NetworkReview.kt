package com.example.fitpal_android.data.remote.model.routine

import com.google.gson.annotations.SerializedName

class NetworkReview(
    @SerializedName("score")
    val score: Int,
    @SerializedName("review")
    val review: String
)