package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.model.Routine
import com.example.fitpal_android.data.repository.RoutineRepository
import kotlinx.coroutines.launch
import kotlin.math.exp

class ExploreRoutinesViewModel(
    private val routineRepository: RoutineRepository
) : ViewModel() {

    var exploreRoutinesState by mutableStateOf(
        ExploreRoutinesState(
            otherRoutines = emptyList()
        )
    )

    init {
        updateRoutines()
    }

    fun updateRoutines() {
        viewModelScope.launch {
            exploreRoutinesState = exploreRoutinesState.copy(isFetching = true, error = "")

            exploreRoutinesState = try {
                routineRepository.fetchRoutines(null, null)
                val routines = routineRepository.getRoutines(null, null)
                exploreRoutinesState.copy(
                    otherRoutines = routines,
                    error = ""
                )
            } catch (e: Exception) {
                exploreRoutinesState.copy(
                    error = e.message ?: "Unknown error"
                )
            }
            exploreRoutinesState = exploreRoutinesState.copy(isFetching = false)
        }
    }

    fun orderBy(orderBy: String, direction: String) {
        viewModelScope.launch {
            exploreRoutinesState = exploreRoutinesState.copy(isFetching = true, error = "")
            try {
                val routines = routineRepository.getRoutines(orderBy, direction)
                exploreRoutinesState = exploreRoutinesState.copy(otherRoutines = routines)
            } catch (e: Exception) {
                // TODO: do smth
            }
            exploreRoutinesState = exploreRoutinesState.copy(isFetching = false)
        }
    }
}