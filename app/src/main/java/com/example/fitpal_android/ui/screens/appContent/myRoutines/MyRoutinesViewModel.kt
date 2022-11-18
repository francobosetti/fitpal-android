package com.example.fitpal_android.ui.screens.appContent.myRoutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.RoutineRepository
import kotlinx.coroutines.launch

class MyRoutinesViewModel(
    private val routineRepository: RoutineRepository
) : ViewModel() {

    var myRoutinesState by mutableStateOf(
        MyRoutinesState(
            myRoutines = emptyList()
        )
    )

    init {
        updateRoutines()
    }

    fun updateRoutines() {
        viewModelScope.launch {
            myRoutinesState = myRoutinesState.copy(isFetching = true, error = "")

            myRoutinesState = try {
                val routines = routineRepository.getCurrentUserRoutines("name", "asc")
                myRoutinesState.copy(
                    myRoutines = routines,
                    error = ""
                )
            } catch (e: Exception) {
                myRoutinesState.copy(
                    error = e.message ?: "Unknown error"
                )
            }
            myRoutinesState = myRoutinesState.copy(isFetching = false)
        }
    }

    fun orderBy(orderBy: String, direction: String) {
        viewModelScope.launch {
            myRoutinesState = myRoutinesState.copy(isFetching = true, error = "")

            try {
                val routines = routineRepository.getCurrentUserRoutines(orderBy, direction)
                myRoutinesState = myRoutinesState.copy(myRoutines = routines)
            } catch (e : Exception) {
                // TODO: do smth
            }
            myRoutinesState = myRoutinesState.copy(isFetching = false)
        }
    }
}