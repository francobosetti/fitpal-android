package com.example.fitpal_android.ui.screens.appContent.favRoutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.FitpalApplication
import com.example.fitpal_android.data.remote.DataSourceException
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.domain.use_case.ApiCodeTranslator
import com.example.fitpal_android.ui.screens.appContent.myRoutines.MyRoutinesState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavRoutinesViewModel(
    private val routineRepository: RoutineRepository
) : ViewModel() {


    var favRoutinesState by mutableStateOf(
        FavRoutinesState(
            favRoutines = emptyList()
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
            favRoutinesState = favRoutinesState.copy(isFetching = true)
            favRoutinesState = try {
                val routines = routineRepository.getFavoriteRoutines()
                favRoutinesState.copy(
                    favRoutines = routines,
                )
            } catch (e: DataSourceException) {
                favRoutinesState.copy(
                    apiMsg = ApiCodeTranslator.translate(e.code)
                )
            }
            favRoutinesState = favRoutinesState.copy(isFetching = false)
        }
    }

    fun dismiss() {
        favRoutinesState = favRoutinesState.copy(apiMsg = null)
    }
}