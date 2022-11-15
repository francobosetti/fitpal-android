package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.model.Routine
import com.example.fitpal_android.data.repository.RoutineRepository
import kotlinx.coroutines.launch

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

            try {
                //routineRepository.fetchRoutines()
                val routines = routineRepository.getRoutines(null, null)
                exploreRoutinesState = exploreRoutinesState.copy(
                    otherRoutines = routines,
                    isFetching = false,
                    error = ""
                )
            } catch (e: Exception) {
                exploreRoutinesState = exploreRoutinesState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun orderBy(orderBy: String, direction: String) {
        viewModelScope.launch {
            val routines = routineRepository.getRoutines(orderBy, direction)
            exploreRoutinesState = exploreRoutinesState.copy(otherRoutines = routines)
        }
    }
}