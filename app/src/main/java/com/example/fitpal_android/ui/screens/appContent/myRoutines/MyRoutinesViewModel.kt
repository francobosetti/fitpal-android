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

            try {
                val routines = routineRepository.getCurrentUserRoutines()
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
}