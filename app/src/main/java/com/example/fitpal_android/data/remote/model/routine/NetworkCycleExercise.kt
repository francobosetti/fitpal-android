package com.example.fitpal_android.data.remote.model.routine

import com.example.fitpal_android.data.model.CycleExercise
import com.example.fitpal_android.data.model.Exercise
import com.example.fitpal_android.data.remote.model.exercise.NetworkExercise
import com.google.gson.annotations.SerializedName

class NetworkCycleExercise (
    @SerializedName("order")
        var order: Int,
    @SerializedName("duration")
        var duration: Int,
    @SerializedName("repetitions")
        var repetitions: Int,
    @SerializedName("exercise")
        var exercise: NetworkExercise,
) {
    fun asModel(videoUrl: String) : CycleExercise {
        return CycleExercise(
            order = order,
            duration = duration,
            repetitions = repetitions,
            exercise = exercise.asModel(videoUrl),
        )
    }
}