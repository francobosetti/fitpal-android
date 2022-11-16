package com.example.fitpal_android.ui.screens.appContent.execRoutine

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.DEPRECATED.Exercise
import com.example.fitpal_android.data.repository.DEPRECATED.RoutineRepository
import com.example.fitpal_android.ui.screens.appContent.execRoutine.Utility.formatTime
import com.example.fitpal_android.util.SettingsManager

class ExecRoutineViewModel(
    routineId: Int?,
    settingsManager: SettingsManager
) : ViewModel() {

    private val defaultId = 1

    private var uiState by mutableStateOf(
        ExecRoutineState(
            routine = RoutineRepository().getRoutineById(routineId ?: defaultId),
            executionMode = settingsManager.getExecutionMode()
        )
    )

    fun getCurrentExercise(): Exercise {
        return uiState.exercises[uiState.currentExerciseIndex]
    }

    fun nextExercise() {
        if (uiState.currentExerciseIndex < uiState.exercises.size - 1) {
            uiState = uiState.copy(
                currentExerciseIndex = uiState.currentExerciseIndex + 1,
                reps = uiState.exercises[uiState.currentExerciseIndex + 1].reps
            )
            stopTimer()
        }
    }

    fun previousExercise() {
        if (uiState.currentExerciseIndex > 0) {
            uiState = uiState.copy(
                currentExerciseIndex = uiState.currentExerciseIndex - 1,
                reps = uiState.exercises[uiState.currentExerciseIndex - 1].reps
            )
            stopTimer()
        }
    }

    fun pauseTimer() {
        uiState.countDownTimer?.cancel()
        uiState = uiState.copy(isPlaying = false, isPaused = true)
    }

    fun stopTimer() {
        uiState.countDownTimer?.cancel()
        handleTimerValues(false, getCurrentExercise().seconds * 1000, 1.0F)
        uiState = uiState.copy(isPlaying = false, isPaused = false)
    }

    fun startTimer() {
        val mills = if (uiState.isPaused)
            uiState.currentTime
        else
            getCurrentExercise().seconds * 1000


        uiState = uiState.copy(countDownTimer = object : CountDownTimer(mills, 1) {
            override fun onTick(millisRemaining: Long) {
                val progressValue =
                    millisRemaining.toFloat() / (getCurrentExercise().seconds * 1000)
                handleTimerValues(true, millisRemaining, progressValue)
            }

            override fun onFinish() {
                stopTimer()
            }
        })

        uiState.countDownTimer?.start()

        uiState = uiState.copy(
            isPlaying = true, isPaused = false
        )
    }

    private fun handleTimerValues(isPlaying: Boolean, mills: Long, progress: Float) {
        uiState = uiState.copy(
            isPlaying = isPlaying,
            currentTime = mills,
            currentProgress = progress
        )
    }

    fun getIsPlaying() = uiState.isPlaying

    fun getProgress() = uiState.currentProgress

    fun getFormatedCurrentTime() = formatTime(uiState.currentTime)

    fun getIsPaused() = uiState.isPaused

    fun getReps() = uiState.reps

    fun getCurrentIndex() = uiState.currentExerciseIndex

    fun getSize() = uiState.exercises.size

    fun isDetailedMode() = uiState.executionMode == ExecRoutineState.DETAILED_MODE

    fun isSimpleMode() = uiState.executionMode == ExecRoutineState.SIMPLE_MODE
}