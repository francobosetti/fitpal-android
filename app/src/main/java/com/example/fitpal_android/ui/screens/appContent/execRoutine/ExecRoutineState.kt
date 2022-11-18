package com.example.fitpal_android.ui.screens.appContent.execRoutine

import android.os.CountDownTimer
import com.example.fitpal_android.data.model.Cycle
import com.example.fitpal_android.data.model.CycleExercise
import com.example.fitpal_android.data.model.Routine

data class ExecRoutineState(
    val routine: Routine? = null,
    val cycles: List<Cycle> = emptyList(),
    val currentCycleIndex: Int = 0,

    val exercises: List<CycleExercise> = emptyList(),
    var currentExerciseIndex: Int = 0,

    var countDownTimer: CountDownTimer? = null,
    var currentTime: Long = 0,
    var currentProgress: Float = 1.00F,
    var isPlaying: Boolean = false,
    var isPaused: Boolean = false,

    val reps: Int = 0,

    val executionMode: Boolean = DETAILED_MODE
) {
    companion object {
        const val DETAILED_MODE = true
        const val SIMPLE_MODE = false
    }
}


