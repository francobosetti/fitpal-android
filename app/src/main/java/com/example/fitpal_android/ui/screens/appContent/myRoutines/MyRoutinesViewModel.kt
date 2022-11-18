package com.example.fitpal_android.ui.screens.appContent.myRoutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.FitpalApplication
import com.example.fitpal_android.data.remote.DataSourceException
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.domain.use_case.ApiCodeTranslator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyRoutinesViewModel(
    private val routineRepository: RoutineRepository
) : ViewModel() {

    var myRoutinesState by mutableStateOf(
        MyRoutinesState(
            myRoutines = emptyList()
        )
    )
        private set

    private var fetchJob: Job? = null

    init {
        updateRoutines()
    }

    fun updateRoutines() {
        if (!FitpalApplication.isUserLoggedIn()) {
            return
        }

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            myRoutinesState = myRoutinesState.copy(isFetching = true)

            myRoutinesState = try {
                val routines = routineRepository.getCurrentUserRoutines("name", "asc")
                myRoutinesState.copy(
                    myRoutines = routines,
                )
            } catch (e: DataSourceException) {
                myRoutinesState.copy(
                    apiMsg = ApiCodeTranslator.translate(e.code)
                )
            }
            myRoutinesState = myRoutinesState.copy(isFetching = false)
        }
    }

    fun orderBy(orderBy: String, direction: String) {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            myRoutinesState = myRoutinesState.copy(isFetching = true)

            myRoutinesState = try {
                val routines = routineRepository.getCurrentUserRoutines(orderBy, direction)
                myRoutinesState.copy(myRoutines = routines)
            } catch (e : DataSourceException) {
                myRoutinesState.copy(
                    apiMsg = ApiCodeTranslator.translate(e.code)
                )
            }
            myRoutinesState = myRoutinesState.copy(isFetching = false)
        }
    }

    fun dismiss() {
        myRoutinesState = myRoutinesState.copy(apiMsg = null)
    }
}