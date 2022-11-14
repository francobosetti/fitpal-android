package com.example.fitpal_android.data.remote.api

import com.example.fitpal_android.data.remote.model.NetworkExercise
import com.example.fitpal_android.data.remote.model.NetworkExerciseVideo
import com.example.fitpal_android.data.remote.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiExerciseService {

    @GET("exercises")
    suspend fun getExercises(@Query("page") page: Int, @Query("size") size: Int): Response<NetworkPagedContent<NetworkExercise>>

    // Only one video per exercise, we use image path because we took bad decision to use image path instead of video path
    @GET("exercises/{exerciseId}/images")
    suspend fun getExerciseVideo(@Path("exerciseId") exerciseId: Int): Response<NetworkPagedContent<NetworkExerciseVideo>>
}