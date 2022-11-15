package com.example.fitpal_android.data.remote.model.routine

import com.google.gson.annotations.SerializedName

class NetworkReviewBody(
    @SerializedName("score")
    val score: Double,
    @SerializedName("review")
    val review: String
)