package com.example.fitpal_android.ui.screens.appContent.favRoutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.ui.screens.appContent.myRoutines.MyRoutinesState
import kotlinx.coroutines.launch

class FavRoutinesViewModel(
    private val routineRepository: RoutineRepository
) : ViewModel() {


    var favRoutinesState by mutableStateOf(
        FavRoutinesState(
            favRoutines = emptyList()
        )
    )

    init {
        viewModelScope.launch {
            favRoutinesState = favRoutinesState.copy(isFetching = true, error = "")
            try {
                val routines = routineRepository.getFavoriteRoutines(null, null)
                favRoutinesState = favRoutinesState.copy(
                    favRoutines = routines,
                    isFetching = false,
                    error = ""
                )
            } catch (e: Exception) {
                favRoutinesState = favRoutinesState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun orderBy(orderBy: String, direction: String) {
        viewModelScope.launch {
            val routines = routineRepository.getFavoriteRoutines(orderBy, direction)
            favRoutinesState = favRoutinesState.copy(favRoutines = routines)
        }
    }
}