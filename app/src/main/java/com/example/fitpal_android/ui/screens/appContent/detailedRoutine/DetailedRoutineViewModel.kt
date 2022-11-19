package com.example.fitpal_android.ui.screens.appContent.detailedRoutine

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.repository.RoutineRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailedRoutineViewModel(
    private val routineRepository: RoutineRepository,
    private val routineId: Int
) : ViewModel() {

    private val validationEventChannel = Channel<DetailedRoutineEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    var detailedRoutineState by mutableStateOf(
        DetailedRoutineState(
            routine = null,
            isFetching = false,
            error = ""
        )
    )
        private set

    init {
        viewModelScope.launch {
            detailedRoutineState = detailedRoutineState.copy(isFetching = true, error = "")

            try {
                val routine = routineRepository.getRoutine(routineId)
                val rating = routineRepository.getRoutineUserScore(routineId)

                detailedRoutineState = detailedRoutineState.copy(
                    routine = routine,
                    userRating = rating,
                    isFetching = false,
                    error = ""
                )
            } catch (e: Exception) {
                detailedRoutineState = detailedRoutineState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }

        }
    }

    fun rateRoutine(rating: Double) {
        viewModelScope.launch {
            detailedRoutineState = detailedRoutineState.copy(isFetching = true, error = "")

            try {
                routineRepository.rateRoutine(routineId, rating * 2)

                routineRepository.fetchRoutine(routineId)
                val routine = routineRepository.getRoutine(routineId)

                detailedRoutineState = detailedRoutineState.copy(
                    routine = routine,
                    isFetching = false,
                    error = ""
                )

                validationEventChannel.send(DetailedRoutineEvent.RoutineRated)

            } catch (e: Exception) {
                detailedRoutineState = detailedRoutineState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
        // reset rating
        detailedRoutineState = detailedRoutineState.copy(reviewRating = 0.0)
    }

    fun toggleFav() {
        viewModelScope.launch {
            detailedRoutineState = detailedRoutineState.copy(isFetching = true, error = "")

            try {
                if (detailedRoutineState.routine?.isFavorite == true) {
                    routineRepository.removeFavoriteRoutine(routineId)
                } else {
                    routineRepository.addFavoriteRoutine(routineId)
                }

                routineRepository.fetchRoutine(routineId)
                val routine = routineRepository.getRoutine(routineId)

                detailedRoutineState = detailedRoutineState.copy(
                    routine = routine,
                    isFetching = false,
                    error = ""
                )

                validationEventChannel.send(DetailedRoutineEvent.FavoriteToggled)
            } catch (e: Exception) {
                detailedRoutineState = detailedRoutineState.copy(
                    isFetching = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun showPopup() {
        detailedRoutineState = detailedRoutineState.copy(showPopup = true)
    }

    fun dismissPopup() {
        detailedRoutineState = detailedRoutineState.copy(showPopup = false)
    }

    fun updateReviewRating(rating: Double) {
        detailedRoutineState = detailedRoutineState.copy(reviewRating = rating)
    }
}