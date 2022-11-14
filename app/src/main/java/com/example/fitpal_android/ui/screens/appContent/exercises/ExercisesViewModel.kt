package com.example.fitpal_android.ui.screens.appContent.exercises

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.model.Exercise
import com.example.fitpal_android.data.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExercisesViewModel(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    var exercisesState by mutableStateOf(
        ExercisesState(
            exercises = emptyList(),
            isFetching = false,
            error = ""
        )
    )

    init {
        viewModelScope.launch {
            exercisesState = exercisesState.copy(isFetching = true, error = "")

            try {
                val exercises = exerciseRepository.getExercises()
                exercisesState = exercisesState.copy(
                    exercises = exercises,
                    isFetching = false,
                    error = ""
                )
            } catch (e: Exception) {
                exercisesState = exercisesState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }

        }
    }

    fun getExercises() : List<Exercise> {
        viewModelScope.launch {
            exercisesState = exercisesState.copy(isFetching = true, error = "")

            try {
                val exercises = exerciseRepository.getExercises()
                exercisesState = exercisesState.copy(
                    exercises = exercises,
                    isFetching = false,
                    error = ""
                )
            } catch (e: Exception) {
                exercisesState = exercisesState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }

        }

        return exercisesState.exercises
    }
}