package com.example.fitpal_android.data.remote

import com.example.fitpal_android.data.remote.api.ApiExerciseService
import com.example.fitpal_android.data.remote.model.NetworkExercise
import com.example.fitpal_android.data.remote.model.NetworkExerciseVideo
import com.example.fitpal_android.data.remote.model.NetworkPagedContent

class ExerciseRemoteDataSource(
    private val apiExerciseService: ApiExerciseService
) : RemoteDataSource() {

    suspend fun getExercises(page: Int, size: Int): NetworkPagedContent<NetworkExercise> {
        return handleApiResponse { apiExerciseService.getExercises(page, size) }
    }

    suspend fun getExerciseVideo(exerciseId: Int): NetworkPagedContent<NetworkExerciseVideo> {
        return handleApiResponse { apiExerciseService.getExerciseVideo(exerciseId) }
    }
}