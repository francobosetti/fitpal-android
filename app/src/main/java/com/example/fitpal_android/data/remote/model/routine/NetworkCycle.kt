package com.example.fitpal_android.data.remote.model.routine

import com.example.fitpal_android.data.model.Cycle
import com.example.fitpal_android.data.model.CycleExercise
import com.google.gson.annotations.SerializedName

class NetworkCycle(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("order")
    var order: Int,
    @SerializedName("repetitions")
    var repetitions: Int,
    @SerializedName("metadata")
    var metadata: NetworkCycleMetadata,
) {
    class NetworkCycleMetadata(
        @SerializedName("rest")
        var rest: Int,
    )

    fun asModel(exercises: List<CycleExercise>): Cycle {
        return Cycle(
            id = id,
            name = name,
            type = type,
            order = order,
            repetitions = repetitions,
            Rest = metadata.rest,
            exercises = exercises,
        )
    }
}