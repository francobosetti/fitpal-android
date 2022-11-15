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
        var imageUrl: String?,
    )

    // Default routine image
    companion object {
        const val DEFAULT_IMAGE = "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8ZXhlcmNpc2V8ZW58MHx8MHx8&w=1000&q=80"
    }

    fun asModel(cycles: List<Cycle>): Routine {

        if (metadata.imageUrl == null || metadata.imageUrl == "") {
            metadata.imageUrl = DEFAULT_IMAGE
        }

        return Routine(
            id = id,
            name = name,
            description = detail,
            imageUrl = metadata.imageUrl!!,
            rating = score.toDouble(),
            tags = metadata.tags,
            cycles = cycles,
        )
    }
}