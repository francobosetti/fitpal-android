package com.example.fitpal_android.data.remote.model.exercise

import com.example.fitpal_android.data.model.Exercise
import com.google.gson.annotations.SerializedName

class NetworkExercise(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("detail")
    var detail: String,
    @SerializedName("metadata")
    var metadata: NetworkExerciseMetadata?,
) {
    class NetworkExerciseMetadata(
        @SerializedName("tags")
        var tags: List<String>,
    )

    fun asModel(imageUrl: String) : Exercise {
        var tags: List<String> = emptyList()
        if(metadata != null) {
            tags = metadata!!.tags
        }
        return Exercise(
            id = id,
            name = name,
            description = detail,
            imageUrl = imageUrl,
            tags = tags,
            seconds = 0,
            reps = 0
        )
    }
}