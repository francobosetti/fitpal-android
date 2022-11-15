package com.example.fitpal_android.data.repository

import com.example.fitpal_android.data.model.Routine
import com.example.fitpal_android.data.remote.ExerciseRemoteDataSource
import com.example.fitpal_android.data.remote.RoutineRemoteDataSource
import com.example.fitpal_android.data.remote.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val routineRemoteDataSource: RoutineRemoteDataSource,
    private val exerciseRemoteDataSource: ExerciseRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) {
    // Constants for the paging
    private val pageSize = 200
    private val page = 0

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
    suspend fun fetchRoutines() {

        // Get favorite routines from the network.
        val favoriteRoutines = routineRemoteDataSource.getFavoriteRoutines(page, pageSize).content

        routineMutex.lock()

        val routines =
            routineRemoteDataSource.getRoutines(page, pageSize).content.map { networkRoutine ->

                val routineCycles =
                    routineRemoteDataSource.getRoutineCycles(networkRoutine.id).content.map { networkCycle ->

                        val cycleExercises =
                            routineRemoteDataSource.getCycleExercises(networkCycle.id).content.map { networkCycleExercise ->
                                val videoUrl =
                                    exerciseRemoteDataSource.getExerciseVideo(networkCycleExercise.exercise.id).content.first().url

                                networkCycleExercise.asModel(videoUrl)
                            }
                        networkCycle.asModel(cycleExercises)
                    }

                networkRoutine.asModel(
                    routineCycles,
                    favoriteRoutines.any { it.id == networkRoutine.id })
            }


        this.routines = routines

        favoriteRoutineMutex.lock()

        this.favoriteRoutines = favoriteRoutines.map { it.id }

        favoriteRoutineMutex.unlock()

        routineMutex.unlock()
    }

    // Fetches the latest favorite routines from the network.
    suspend fun fetchFavoriteRoutines() {
        favoriteRoutineMutex.lock()

        val favoriteRoutines = routineRemoteDataSource.getFavoriteRoutines(
            page,
            pageSize
        ).content.map { networkRoutine ->
            networkRoutine.id
        }

        this.favoriteRoutines = favoriteRoutines

        favoriteRoutineMutex.unlock()
    }

    // Fetches the latest user routines from the network.
    suspend fun fetchCurrentUserRoutines() {
        currentRoutineMutex.lock()

        val currentUserRoutines = routineRemoteDataSource.getCurrentUserRoutines(
            page,
            pageSize
        ).content.map { networkRoutine ->
            networkRoutine.id
        }

        this.userRoutines = currentUserRoutines

        currentRoutineMutex.unlock()
    }

    // Returns the cached routines.
    suspend fun getRoutines(): List<Routine> {
        if (routines.isEmpty()) {
            fetchRoutines()
        }

        return routineMutex.withLock { this.routines }
    }

    // Returns the routine with the given id.
    suspend fun getRoutine(id: Int): Routine {
        if (routines.isEmpty()) {
            fetchRoutines()
        }

        return routineMutex.withLock { this.routines.first { it.id == id } }
    }

    // Returns the cached favorite routines.
    suspend fun getFavoriteRoutines(): List<Routine> {
        if (routines.isEmpty()) {
            fetchRoutines()
        }

        if (favoriteRoutines.isEmpty()) {
            fetchFavoriteRoutines()
        }

        // Get routines that have the same id as the favorite routines.
        return routineMutex.withLock {
            this.routines.filter { routine ->
                favoriteRoutines.contains(
                    routine.id
                )
            }
        }
    }

    // Returns the cached user routines.
    suspend fun getCurrentUserRoutines(): List<Routine> {
        if (routines.isEmpty()) {
            fetchRoutines()
        }

        if (userRoutines.isEmpty()) {
            fetchCurrentUserRoutines()
        }

        // Get routines that have the same id as the user routines.
        return routineMutex.withLock {
            this.routines.filter { routine ->
                userRoutines.contains(
                    routine.id
                )
            }
        }
    }

    // Returns an average of the score of the routine.
    suspend fun getRoutineScore(routineId: Int): Double {
        val routineReviews =
            routineRemoteDataSource.getRoutineReviews(routineId).content.map { networkRoutineReview ->
                networkRoutineReview.score
            }

        return routineReviews.average()
    }

    // Returns the review of the user for the routine.
    suspend fun getRoutineUserScore(routineId: Int): Double? {

        return try {
            val routineReview =
                routineRemoteDataSource.getRoutineReviews(routineId).content.first { networkRoutineReview ->
                    networkRoutineReview.user.id == userRemoteDataSource.getCurrentUser().id
                }

            routineReview.score

        } catch (e: NoSuchElementException) {
            null
        }
    }

    // ---------------- Setters ----------------

    // Adds a routine to the favorite routines.
    suspend fun addFavoriteRoutine(routineId: Int) {
        // Add the routine id to the favorite routines.
        routineRemoteDataSource.addFavoriteRoutine(routineId)
        fetchFavoriteRoutines()

    }

    // Removes a routine from the favorite routines.
    suspend fun removeFavoriteRoutine(routineId: Int) {
        // Remove the routine id from the favorite routines.
        routineRemoteDataSource.removeFavoriteRoutine(routineId)
        fetchFavoriteRoutines()
    }

    // Rates a routine.
    suspend fun rateRoutine(routineId: Int, rating: Double) {

        // TODO: si el usuario ya ha valorado la rutina, se deberia actualizar la valoracion, la api no t permite hacerlo, nose si hacer q el usuario vote muchas veces o no dejarle

        routineRemoteDataSource.addRoutineReview(routineId, rating)
    }

}