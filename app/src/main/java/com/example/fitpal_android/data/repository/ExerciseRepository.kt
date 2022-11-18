package com.example.fitpal_android.data.repository

import com.example.fitpal_android.data.model.Exercise
import com.example.fitpal_android.data.remote.ExerciseRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ExerciseRepository(
    private val exerciseRemoteDataSource: ExerciseRemoteDataSource
) {
    // Constants for the paging
    private val pageSize = 200
    private val page = 0

    // Mutex to make writes to cached values thread-safe.
    private val exerciseMutex = Mutex()
    // Cache of the latest exercises got from the network.
    private var exercises: List<Exercise> = emptyList()

    // Fetches the latest exercises from the network.
    suspend fun fetchExercises() {
        exerciseMutex.lock()

        val exercises = exerciseRemoteDataSource.getExercises(page, pageSize).content.map { networkExercise ->
            val imageUrl = exerciseRemoteDataSource.getExerciseImage(networkExercise.id).content.first().url
            networkExercise.asModel(imageUrl)
        }
        this.exercises = exercises

        exerciseMutex.unlock()
    }

    // Returns the latest exercises from the cache.
    suspend fun getExercises() : List<Exercise> {
        if (exercises.isEmpty()) {
            fetchExercises()
        }

        return exerciseMutex.withLock { this.exercises }
    }

    // Returns the exercise with the given id from the cache.
    suspend fun getExercise(id: Int) : Exercise? {
        if (exercises.isEmpty()) {
            fetchExercises()
        }

        return exerciseMutex.withLock { this.exercises.find { it.id == id } }
    }

    // Clears cache
    suspend fun resetRepository() {
        exerciseMutex.withLock {
            this.exercises = emptyList()
        }
    }
}