package com.example.fitpal_android.data.remote.model.routine

import com.example.fitpal_android.data.model.Cycle
import com.example.fitpal_android.data.model.Routine
import com.google.gson.annotations.SerializedName
import java.util.*

class NetworkRoutine(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("detail")
    var detail: String,
    @SerializedName("date")
    var date: Date?,
    @SerializedName("score")
    var score: Int,
    @SerializedName("difficulty")
    var difficulty: String,
    @SerializedName("metadata")
    var metadata: NetworkRoutineMetadata,
) {
    class NetworkRoutineMetadata(
        @SerializedName("tags")
        var tags: List<String>,
        @SerializedName("imageUrl")
        var imageUrl: String,
    )

    fun asModel(cycles: List<Cycle>): Routine {
        return Routine(
            id = id,
            name = name,
            description = detail,
            imageUrl = metadata.imageUrl,
            rating = score.toDouble(),
            tags = metadata.tags,
            cycles = cycles,
        )
    }
}