package com.example.fitpal_android.ui.screens.appContent.execRoutine

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpal_android.data.model.CycleExercise
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.util.SettingsManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ExecRoutineViewModel(
    private val routineId: Int,
    private val routineRepository: RoutineRepository,
    settingsManager: SettingsManager
) : ViewModel() {

    var uiState by mutableStateOf(
        ExecRoutineState(
            executionMode = settingsManager.getExecutionMode()
        )
    )
        private set

    private var fetchJob: Job? = null

    init {
        updateExecRoutine()
    }

    private fun updateExecRoutine(){
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            val routine = routineRepository.getRoutine(routineId)
            uiState = uiState.copy(
                routine = routine,
                cycles = routine.cycles,
                exercises = routine.cycles[uiState.currentCycleIndex].exercises,
                reps = routine.cycles[uiState.currentCycleIndex].exercises[uiState.currentExerciseIndex].repetitions,
                currentTime = (routine.cycles[uiState.currentCycleIndex].exercises[uiState.currentExerciseIndex].duration * 1000).toLong()
            )
        }
    }

    private fun getCurrentExercise(): CycleExercise {
        return uiState.exercises[uiState.currentExerciseIndex]
    }

    fun nextExercise() {
        if (uiState.currentExerciseIndex < uiState.exercises.size - 1) {
            uiState = uiState.copy(
                reps = uiState.exercises[uiState.currentExerciseIndex + 1].repetitions,
                currentExerciseIndex = uiState.currentExerciseIndex + 1
            )
            stopTimer()
        }
    }

    fun previousExercise() {
        if (uiState.currentExerciseIndex > 0) {
            uiState = uiState.copy(
                reps = uiState.exercises[uiState.currentExerciseIndex - 1].repetitions,
                currentExerciseIndex = uiState.currentExerciseIndex - 1
            )
            stopTimer()
        }
        else if(uiState.currentCycleIndex > 0){
            uiState = uiState.copy(
                currentCycleIndex = uiState.currentCycleIndex - 1,
                exercises = uiState.cycles[uiState.currentCycleIndex - 1].exercises,
                reps = uiState.cycles[uiState.currentCycleIndex - 1].exercises[uiState.cycles[uiState.currentCycleIndex - 1].exercises.size - 1].repetitions,
                currentExerciseIndex = uiState.cycles[uiState.currentCycleIndex -1].exercises.size - 1
            )
            stopTimer()
        }
    }

    fun nextCycle() {
        if (uiState.currentCycleIndex < uiState.cycles.size - 1) {
            uiState = uiState.copy(
                exercises = uiState.cycles[uiState.currentCycleIndex + 1].exercises,
                currentCycleIndex = uiState.currentCycleIndex + 1,
                reps = uiState.cycles[uiState.currentCycleIndex + 1].exercises[0].repetitions,
                currentExerciseIndex = 0
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
        handleTimerValues(false, (getCurrentExercise().duration * 1000).toLong(), 1.0F)
        uiState = uiState.copy(isPlaying = false, isPaused = false)
    }

    fun startTimer() {
        val mills = if(uiState.isPaused)
            uiState.currentTime
        else
            (getCurrentExercise().duration * 1000).toLong()


        uiState = uiState.copy(countDownTimer = object : CountDownTimer(mills, 1) {
            override fun onTick(millisRemaining: Long) {
                val progressValue = millisRemaining.toFloat() / (getCurrentExercise().duration * 1000)
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

    private fun formatTime(mills : Long): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(mills),
            TimeUnit.MILLISECONDS.toSeconds(mills) % 60
        )
    }

    fun getRoutineName() = uiState.routine?.name

    fun getCurrentExerciseName() = uiState.exercises[uiState.currentExerciseIndex].exercise.name

    fun getCurrentCycleName() = uiState.cycles[uiState.currentCycleIndex].name

    fun getIsPlaying() = uiState.isPlaying

    fun getProgress() = uiState.currentProgress

    fun getFormatedCurrentTime() = formatTime(uiState.currentTime)

    fun getIsPaused() = uiState.isPaused

    fun getReps() = uiState.reps

    fun getCycles() = uiState.cycles

    fun getCurrentExerciseIndex() = uiState.currentExerciseIndex

    fun getCurrentCycleIndex() = uiState.currentCycleIndex

    fun getExercisesSize() = uiState.exercises.size

    fun getCyclesSize() = uiState.cycles.size

    fun isDetailedMode() = uiState.executionMode == ExecRoutineState.DETAILED_MODE

    fun isSimpleMode() = uiState.executionMode == ExecRoutineState.SIMPLE_MODE

    fun getCycle(cycleIndex: Int) = uiState.cycles[cycleIndex]

}