package com.example.fitpal_android.data.repository

import com.example.fitpal_android.data.model.Routine
import com.example.fitpal_android.data.remote.ExerciseRemoteDataSource
import com.example.fitpal_android.data.remote.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val routineRemoteDataSource: RoutineRemoteDataSource,
    private val exerciseRemoteDataSource: ExerciseRemoteDataSource
) {
    // Constants for the paging
    private val pageSize = 200
    private val page = 0

    //Constants for default ordering values
    private val defaultOrdering = "date"
    private val defaultDirection = "asc"

    // Mutex to make writes to cached values thread-safe.
    private val routineMutex = Mutex()
    private val favoriteRoutineMutex = Mutex()
    private val currentRoutineMutex = Mutex()

    // Cache of the latest routines got from the network.
    private var routines: List<Routine> = emptyList()
    // Cache of the latest favorite routines got from the network using id.
    private var favoriteRoutines: List<Int> = emptyList()
    // Cache of the latest user routines got from the network using id.
    private var userRoutines: List<Int> = emptyList()

    // Fetches the latest routines from the network.
    suspend fun fetchRoutines(orderBy : String, direction : String) {
        routineMutex.lock()

        val routines = routineRemoteDataSource.getRoutines(page, pageSize, orderBy, direction).content.map { networkRoutine ->

            val routineCycles = routineRemoteDataSource.getRoutineCycles(networkRoutine.id).content.map { networkCycle ->

                val cycleExercises = routineRemoteDataSource.getCycleExercises(networkCycle.id).content.map { networkCycleExercise ->
                    val videoUrl = exerciseRemoteDataSource.getExerciseVideo(networkCycleExercise.exercise.id).content.first().url

                    networkCycleExercise.asModel(videoUrl)
                }
                networkCycle.asModel(cycleExercises)
            }

            networkRoutine.asModel(routineCycles)
        }
        this.routines = routines

        routineMutex.unlock()
    }

    // Fetches the latest favorite routines from the network.
    suspend fun fetchFavoriteRoutines(orderBy : String, direction : String) {
        favoriteRoutineMutex.lock()

        val favoriteRoutines = routineRemoteDataSource.getFavoriteRoutines(page, pageSize, orderBy, direction).content.map { networkRoutine ->
            networkRoutine.id
        }

        this.favoriteRoutines = favoriteRoutines

        favoriteRoutineMutex.unlock()
    }

    // Fetches the latest user routines from the network.
    suspend fun fetchCurrentUserRoutines(orderBy : String, direction : String) {
        currentRoutineMutex.lock()

        val currentUserRoutines = routineRemoteDataSource.getCurrentUserRoutines(page, pageSize, orderBy, direction).content.map { networkRoutine ->
            networkRoutine.id
        }

        this.userRoutines = currentUserRoutines

        currentRoutineMutex.unlock()
    }

    // Returns the cached routines.
    suspend fun getRoutines(orderBy : String?, direction : String?): List<Routine> {
        val resp = handleParameters(orderBy, direction)

        //TODO: Check if deleting empty check is correct, one way to make ordering work
        //if (routines.isEmpty()) {
            fetchRoutines(resp.first, resp.second)
        //}

        return routineMutex.withLock { this.routines }
    }

    // Returns the cached favorite routines.
    suspend fun getFavoriteRoutines(orderBy : String?, direction : String?): List<Routine> {
        val resp = handleParameters(orderBy, direction)

        //if (routines.isEmpty()) {
            fetchRoutines(resp.first, resp.second)
        //}

        //if (favoriteRoutines.isEmpty()) {
            fetchFavoriteRoutines(resp.first, resp.second)
       // }

        // Get routines that have the same id as the favorite routines.
        return routineMutex.withLock { this.routines.filter { routine -> favoriteRoutines.contains(routine.id) } }
    }

    // Returns the cached user routines.
    suspend fun getCurrentUserRoutines(orderBy : String?, direction : String?): List<Routine> {
        val resp = handleParameters(orderBy, direction)

        //if (routines.isEmpty()) {
            fetchRoutines(resp.first, resp.second)
        //}

        //if (userRoutines.isEmpty()) {
            fetchCurrentUserRoutines(resp.first, resp.second)
        //}

        // Get routines that have the same id as the user routines.
        return routineMutex.withLock { this.routines.filter { routine -> userRoutines.contains(routine.id) } }
    }

    // Returns an average of the score of the routine.
    suspend fun getRoutineScore(routineId: Int): Double {
        val routineReviews = routineRemoteDataSource.getRoutineReviews(routineId).content.map { networkRoutineReview ->
            networkRoutineReview.score
        }

        return routineReviews.average()
    }

    private fun handleParameters(orderBy : String?, direction : String?) : Pair<String, String> {
        var ordering = defaultOrdering
        var dir = defaultDirection

        if (orderBy != null)
            ordering = orderBy

        if (direction != null)
            dir = direction

        return Pair(ordering, dir)
    }

    // ---------------- Setters ----------------

    // Adds a routine to the favorite routines.
    suspend fun addFavoriteRoutine(routineId: Int) {
        favoriteRoutineMutex.lock()

        // Add the routine id to the favorite routines.
        routineRemoteDataSource.addFavoriteRoutine(routineId)
        fetchFavoriteRoutines(defaultOrdering, defaultDirection)

        favoriteRoutineMutex.unlock()
    }

    // Removes a routine from the favorite routines.
    suspend fun removeFavoriteRoutine(routineId: Int) {
        favoriteRoutineMutex.lock()

        // Remove the routine id from the favorite routines.
        routineRemoteDataSource.removeFavoriteRoutine(routineId)
        fetchFavoriteRoutines(defaultOrdering, defaultDirection)

        favoriteRoutineMutex.unlock()
    }

    // Rates a routine.
    suspend fun rateRoutine(routineId: Int, rating: Int) {
        routineRemoteDataSource.addRoutineReview(routineId, rating)
    }

}