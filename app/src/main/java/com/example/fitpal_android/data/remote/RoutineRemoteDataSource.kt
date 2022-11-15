package com.example.fitpal_android.data.remote

import com.example.fitpal_android.data.remote.api.ApiRoutineService
import com.example.fitpal_android.data.remote.model.NetworkPagedContent
import com.example.fitpal_android.data.remote.model.routine.*

class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService
) : RemoteDataSource() {

    suspend fun getCurrentUserRoutines(page: Int, size: Int, orderBy : String, direction : String): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse { apiRoutineService.getCurrentUserRoutines(page, size, orderBy, direction) }
    }

    suspend fun getRoutines(page: Int, size: Int, orderBy : String, direction : String): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse { apiRoutineService.getRoutines(page, size, orderBy, direction) }
    }

    suspend fun getFavoriteRoutines(page: Int, size: Int): NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse { apiRoutineService.getFavoriteRoutines(page, size) }
    }

    suspend fun addFavoriteRoutine(routineId: Int) {
        return handleApiResponse { apiRoutineService.addFavoriteRoutine(routineId) }
    }

    suspend fun removeFavoriteRoutine(routineId: Int) {
        return handleApiResponse { apiRoutineService.removeFavoriteRoutine(routineId) }
    }

    suspend fun getRoutineCycles(routineId: Int): NetworkPagedContent<NetworkCycle> {
        return handleApiResponse { apiRoutineService.getRoutineCycles(routineId) }
    }

    suspend fun getCycleExercises(cycleId: Int): NetworkPagedContent<NetworkCycleExercise> {
        return handleApiResponse { apiRoutineService.getCycleExercises(cycleId) }
    }

    suspend fun getRoutineReviews(routineId: Int): NetworkPagedContent<NetworkReviewResponse> {
        return handleApiResponse { apiRoutineService.getRoutineReviews(routineId) }
    }

    suspend fun addRoutineReview(routineId: Int, review: Double) {
        return handleApiResponse {
            apiRoutineService.addRoutineReview(
                routineId,
                NetworkReviewBody(review, "")
            )
        }

    }
}