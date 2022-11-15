package com.example.fitpal_android.data.remote.api

import com.example.fitpal_android.data.remote.model.NetworkPagedContent
import com.example.fitpal_android.data.remote.model.routine.*
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutineService {
    @GET("users/current/routines")
    suspend fun getCurrentUserRoutines(@Query("page") page: Int, @Query("size") size: Int, @Query("orderBy") orderBy : String, @Query("direction") direction : String): Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines")
    suspend fun getRoutines(@Query("page") page: Int, @Query("size") size: Int, @Query("orderBy") orderBy : String, @Query("direction") direction : String): Response<NetworkPagedContent<NetworkRoutine>>

    @GET("favorites")
    suspend fun getFavoriteRoutines(@Query("page") page: Int, @Query("size") size: Int, @Query("orderBy") orderBy : String, @Query("direction") direction : String): Response<NetworkPagedContent<NetworkRoutine>>

    @POST("favorites/{routineId}")
    suspend fun addFavoriteRoutine(@Path("routineId") routineId: Int): Response<Unit>

    @DELETE("favourites/{routineId}")
    suspend fun removeFavoriteRoutine(@Path("routineId") routineId: Int): Response<Unit>

    @GET("routines/{routineId}/cycles")
    suspend fun getRoutineCycles(@Path("routineId") routineId: Int): Response<NetworkPagedContent<NetworkCycle>>

    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(@Path("cycleId") cycleId: Int): Response<NetworkPagedContent<NetworkCycleExercise>>

    @GET("reviews/{routineId}")
    suspend fun getRoutineReviews(@Path("routineId") routineId: Int): Response<NetworkPagedContent<NetworkReviewResponse>>

    @POST("reviews/{routineId}")
    suspend fun addRoutineReview(@Path("routineId") routineId: Int, @Body review: NetworkReviewBody): Response<Unit>

}