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
        viewModelScope.launch {
            myRoutinesState = myRoutinesState.copy(isFetching = true, error = "")

            try {
                val routines = routineRepository.getCurrentUserRoutines("name", "asc")
                myRoutinesState = myRoutinesState.copy(
                    myRoutines = routines,
                    isFetching = false,
                    error = ""
                )
            } catch (e: Exception) {
                myRoutinesState = myRoutinesState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun orderBy(orderBy: String, direction: String) {
        viewModelScope.launch {
            val routines = routineRepository.getCurrentUserRoutines(orderBy, direction)
            myRoutinesState = myRoutinesState.copy(myRoutines = routines)
        }
    }
}