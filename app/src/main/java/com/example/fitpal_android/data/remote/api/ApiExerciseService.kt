package com.example.fitpal_android.data.remote.api

import com.example.fitpal_android.data.remote.model.exercise.NetworkExercise
import com.example.fitpal_android.data.remote.model.exercise.NetworkExerciseImage
import com.example.fitpal_android.data.remote.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiExerciseService {

    @GET("exercises")
    suspend fun getExercises(@Query("page") page: Int, @Query("size") size: Int): Response<NetworkPagedContent<NetworkExercise>>

    // Only one image per exercise
    @GET("exercises/{exerciseId}/images")
    suspend fun getExerciseImage(@Path("exerciseId") exerciseId: Int): Response<NetworkPagedContent<NetworkExerciseImage>>
}