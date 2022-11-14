package com.example.fitpal_android.ui.screens.appContent.detailedExercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.ExerciseRepository
import kotlinx.coroutines.launch

class DetailedExerciseViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val exerciseId: Int
) : ViewModel() {

    var detailedExerciseState by mutableStateOf(
        DetailedExerciseState(
            exercise = null,
            isFetching = false,
            error = ""
        )
    )

    init {
        viewModelScope.launch {
            detailedExerciseState = detailedExerciseState.copy(isFetching = true, error = "")

            try {
                val exercise = exerciseRepository.getExercise(exerciseId)
                detailedExerciseState = detailedExerciseState.copy(
                    exercise = exercise,
                    isFetching = false,
                    error = ""
                )
            } catch (e: Exception) {
                detailedExerciseState = detailedExerciseState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }

        }
    }
}