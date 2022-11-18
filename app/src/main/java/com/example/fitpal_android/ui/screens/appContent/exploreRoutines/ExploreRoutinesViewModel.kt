package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.FitpalApplication
import com.example.fitpal_android.data.model.Routine
import com.example.fitpal_android.data.remote.DataSourceException
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.domain.use_case.ApiCodeTranslator
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.exp
import kotlin.math.expm1

class ExploreRoutinesViewModel(
    private val routineRepository: RoutineRepository
) : ViewModel() {

    var exploreRoutinesState by mutableStateOf(
        ExploreRoutinesState(
            otherRoutines = emptyList()
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
            exploreRoutinesState = exploreRoutinesState.copy(isFetching = true)

            exploreRoutinesState = try {
                routineRepository.fetchRoutines(null, null)
                val routines = routineRepository.getRoutines(null, null)
                exploreRoutinesState.copy(
                    otherRoutines = routines,
                )
            } catch (e: DataSourceException) {
                exploreRoutinesState.copy(
                    apiMsg = ApiCodeTranslator.translate(e.code)
                )
            }
            exploreRoutinesState = exploreRoutinesState.copy(isFetching = false)
        }
    }

    fun orderBy(orderBy: String, direction: String) {

        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            exploreRoutinesState = exploreRoutinesState.copy(isFetching = true)
            exploreRoutinesState = try {
                val routines = routineRepository.getRoutines(orderBy, direction)
                exploreRoutinesState.copy(otherRoutines = routines)
            } catch (e: DataSourceException) {
                exploreRoutinesState.copy(
                    apiMsg = ApiCodeTranslator.translate(e.code)
                )
            }
            exploreRoutinesState = exploreRoutinesState.copy(isFetching = false)
        }
    }

    fun dismiss() {
        exploreRoutinesState = exploreRoutinesState.copy(apiMsg = null)
    }
}