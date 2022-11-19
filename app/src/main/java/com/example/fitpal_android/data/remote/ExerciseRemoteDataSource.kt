package com.example.fitpal_android.data.remote

import com.example.fitpal_android.data.remote.api.ApiExerciseService
import com.example.fitpal_android.data.remote.model.exercise.NetworkExercise
import com.example.fitpal_android.data.remote.model.exercise.NetworkExerciseImage
import com.example.fitpal_android.data.remote.model.NetworkPagedContent

class ExerciseRemoteDataSource(
    private val apiExerciseService: ApiExerciseService
) : RemoteDataSource() {

    suspend fun getExercises(page: Int, size: Int): NetworkPagedContent<NetworkExercise> {
        return handleApiResponse { apiExerciseService.getExercises(page, size) }
    }

    suspend fun getExerciseImage(exerciseId: Int): NetworkPagedContent<NetworkExerciseImage> {
        return handleApiResponse { apiExerciseService.getExerciseImage(exerciseId) }
    }
}