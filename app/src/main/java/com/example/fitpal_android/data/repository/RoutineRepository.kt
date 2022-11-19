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

    //Constants for default ordering values
    private val defaultOrdering = "date"
    private val defaultDirection = "asc" // TODO: CHECK

    // Mutex to make writes to cached values thread-safe.
    private val routineMutex = Mutex()
    private val favoriteRoutineMutex = Mutex()
    private val currentRoutineMutex = Mutex()

    // Cache of the latest routines got from the network.
    private var routines: List<Routine> = emptyList()

    // Cache of the latest favorite routines got from the network using id.
    private var favoriteRoutines: List<Routine> = emptyList()

    // Cache of the latest user routines got from the network using id.
    private var userRoutines: List<Routine> = emptyList()

    // Fetches the latest routines from the network.
    suspend fun fetchRoutines(orderBy: String?, direction: String?) {
        val resp = handleParameters(orderBy, direction)

        // Get favorite routines from the network.
        val favoriteRoutines = routineRemoteDataSource.getFavoriteRoutines(page, pageSize).content

        routineMutex.lock()

        val routines =
            routineRemoteDataSource.getRoutines(
                page,
                pageSize,
                resp.first,
                resp.second
            ).content.map { networkRoutine ->

                val routineCycles =
                    routineRemoteDataSource.getRoutineCycles(networkRoutine.id).content.map { networkCycle ->

                        val cycleExercises =
                            routineRemoteDataSource.getCycleExercises(networkCycle.id).content.map { networkCycleExercise ->
                                val videoUrl =
                                    exerciseRemoteDataSource.getExerciseImage(networkCycleExercise.exercise.id).content.first().url

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

        this.favoriteRoutines = favoriteRoutines.map {
            networkRoutine ->

            val routineCycles =
                routineRemoteDataSource.getRoutineCycles(networkRoutine.id).content.map { networkCycle ->

                    val cycleExercises =
                        routineRemoteDataSource.getCycleExercises(networkCycle.id).content.map { networkCycleExercise ->
                            val videoUrl =
                                exerciseRemoteDataSource.getExerciseImage(networkCycleExercise.exercise.id).content.first().url

                            networkCycleExercise.asModel(videoUrl)
                        }
                    networkCycle.asModel(cycleExercises)
                }

            networkRoutine.asModel(
                routineCycles,
                favoriteRoutines.any { it.id == networkRoutine.id })
        }



        favoriteRoutineMutex.unlock()

        routineMutex.unlock()
    }

    // Fetches a routine from the network using id.
    suspend fun fetchRoutine(routineId: Int) {
        val networkRoutine = routineRemoteDataSource.getRoutine(routineId)

        val routineCycles =
            routineRemoteDataSource.getRoutineCycles(networkRoutine.id).content.map { networkCycle ->

                val cycleExercises =
                    routineRemoteDataSource.getCycleExercises(networkCycle.id).content.map { networkCycleExercise ->
                        val videoUrl =
                            exerciseRemoteDataSource.getExerciseImage(networkCycleExercise.exercise.id).content.first().url

                        networkCycleExercise.asModel(videoUrl)
                    }
                networkCycle.asModel(cycleExercises)
            }

        val routine = networkRoutine.asModel(routineCycles, favoriteRoutines.any { it.id == routineId })

        // Replace the routine in the cache with the new one.
        routineMutex.withLock {
            routines = routines.map { if (it.id == routineId) routine else it }
        }

        // If the routine is a favorite o is not favorite anymore, replace it in the cache.
        favoriteRoutineMutex.withLock {
            favoriteRoutines = if (routine.isFavorite) {
                favoriteRoutines.map { if (it.id == routineId) routine else it }
            } else {
                favoriteRoutines.filter { it.id != routineId }
            }
        }
    }

    // Fetches the latest favorite routines from the network.
    suspend fun fetchFavoriteRoutines() {
        favoriteRoutineMutex.lock()

        val favoriteRoutines = routineRemoteDataSource.getFavoriteRoutines(
            page,
            pageSize,
        ).content.map { networkRoutine ->

            val routineCycles =
                routineRemoteDataSource.getRoutineCycles(networkRoutine.id).content.map { networkCycle ->

                    val cycleExercises =
                        routineRemoteDataSource.getCycleExercises(networkCycle.id).content.map { networkCycleExercise ->
                            val videoUrl =
                                exerciseRemoteDataSource.getExerciseImage(networkCycleExercise.exercise.id).content.first().url

                            networkCycleExercise.asModel(videoUrl)
                        }
                    networkCycle.asModel(cycleExercises)
                }

            networkRoutine.asModel(
                routineCycles,
                favoriteRoutines.any { it.id == networkRoutine.id })
        }

        this.favoriteRoutines = favoriteRoutines

        favoriteRoutineMutex.unlock()
    }

    // Fetches the latest user routines from the network.
    suspend fun fetchCurrentUserRoutines(orderBy: String?, direction: String?) {
        val resp = handleParameters(orderBy, direction)

        currentRoutineMutex.lock()

        val currentUserRoutines = routineRemoteDataSource.getCurrentUserRoutines(
            page,
            pageSize,
            resp.first,
            resp.second
        ).content.map { networkRoutine ->

            val routineCycles =
                routineRemoteDataSource.getRoutineCycles(networkRoutine.id).content.map { networkCycle ->

                    val cycleExercises =
                        routineRemoteDataSource.getCycleExercises(networkCycle.id).content.map { networkCycleExercise ->
                            val videoUrl =
                                exerciseRemoteDataSource.getExerciseImage(networkCycleExercise.exercise.id).content.first().url

                            networkCycleExercise.asModel(videoUrl)
                        }
                    networkCycle.asModel(cycleExercises)
                }

            networkRoutine.asModel(
                routineCycles,
                favoriteRoutines.any { it.id == networkRoutine.id })
        }

        this.userRoutines = currentUserRoutines

        currentRoutineMutex.unlock()
    }

    // Returns the cached routines.
    suspend fun getRoutines(orderBy: String?, direction: String?): List<Routine> {

        //TODO: Check if deleting empty check is correct, one way to make ordering work
        //if (routines.isEmpty()) {
        fetchRoutines(orderBy, direction)
        //}

        return routineMutex.withLock { this.routines }
    }

    // Returns the routine with the given id.
    suspend fun getRoutine(id: Int): Routine {
        if (routines.isEmpty()) {
            fetchRoutines(defaultOrdering, defaultDirection)
        }

        return routineMutex.withLock { this.routines.first { it.id == id } }
    }

    // Returns the cached favorite routines.
    suspend fun getFavoriteRoutines(): List<Routine> {

        if (routines.isEmpty()) {
            fetchRoutines(null, null)
        }

        if (favoriteRoutines.isEmpty()) {
            fetchFavoriteRoutines()
        }

        // Get routines that have the same id as the favorite routines.
        return favoriteRoutines
    }

    // Returns the cached user routines.
    suspend fun getCurrentUserRoutines(orderBy: String?, direction: String?): List<Routine> {

        if (userRoutines.isEmpty() || orderBy?.equals(defaultOrdering) == false || direction?.equals(defaultDirection) == false) {
            fetchCurrentUserRoutines(orderBy, direction)
        }

        // Get routines that have the same id as the user routines.
        return userRoutines
    }

    // Returns an average of the score of the routine.
    suspend fun getRoutineScore(routineId: Int): Double {
        val routineReviews =
            routineRemoteDataSource.getRoutineReviews(routineId).content.map { networkRoutineReview ->
                networkRoutineReview.score
            }

        return routineReviews.average()
    }

    private fun handleParameters(orderBy: String?, direction: String?): Pair<String, String> {
        var ordering = defaultOrdering
        var dir = defaultDirection

        if (orderBy != null)
            ordering = orderBy

        if (direction != null)
            dir = direction

        return Pair(ordering, dir)
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
        routineRemoteDataSource.addRoutineReview(routineId, rating)
    }

    // Clears cache
    suspend fun resetRepository() {

        // Routines list
        routineMutex.withLock {
            this.routines = emptyList()
        }

        // Favorites list
        favoriteRoutineMutex.withLock {
            this.favoriteRoutines = emptyList()
        }

        // Current user list
        currentRoutineMutex.withLock {
            this.userRoutines = emptyList()
        }
    }

}