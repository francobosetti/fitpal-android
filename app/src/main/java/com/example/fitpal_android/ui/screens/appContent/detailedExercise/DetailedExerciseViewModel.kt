package com.example.fitpal_android.ui.screens.appContent.detailedExercise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.remote.DataSourceException
import com.example.fitpal_android.data.repository.ExerciseRepository
import com.example.fitpal_android.domain.use_case.ApiCodeTranslator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailedExerciseViewModel(
    private val exerciseRepository: ExerciseRepository,
    private val exerciseId: Int
) : ViewModel() {

    var detailedExerciseState by mutableStateOf(
        DetailedExerciseState(
            exercise = null,
            isFetching = false,
        )
    )
    private set

    private var fetchJob: Job? = null

    init {
        updateDetailedExercise()
    }

    private fun updateDetailedExercise(){
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            detailedExerciseState = detailedExerciseState.copy(isFetching = true)

            detailedExerciseState = try {
                val exercise = exerciseRepository.getExercise(exerciseId)
                detailedExerciseState.copy(
                    exercise = exercise,
                )
            } catch (e: DataSourceException) {
                detailedExerciseState.copy(
                    apiMsg = ApiCodeTranslator.translate(e.code)
                )
            }
            detailedExerciseState = detailedExerciseState.copy(isFetching = false)
        }
    }

    fun dismiss() {
        detailedExerciseState = detailedExerciseState.copy(apiMsg = null)
    }
}