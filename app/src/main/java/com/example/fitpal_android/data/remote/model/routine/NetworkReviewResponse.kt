package com.example.fitpal_android.data.remote.model.routine

import com.google.gson.annotations.SerializedName

class NetworkReviewResponse(
    @SerializedName("score")
    val score: Double,
    @SerializedName("user")
    val user: NetworkReviewUser
) {
    class NetworkReviewUser(
        @SerializedName("id")
        val id: Int,
    )
}