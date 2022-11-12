package com.example.fitpal_android.ui.screens.appContent.execRoutine

import android.os.CountDownTimer
import com.example.fitpal_android.data.repository.Exercise
import com.example.fitpal_android.data.repository.Routine
import com.example.fitpal_android.data.repository.RoutineRepository
import java.util.concurrent.TimeUnit

data class ExecRoutineState(
    val routine: Routine?,
    val exercises: List<Exercise> = RoutineRepository().getMyRoutines().find { it.id == routine?.id }?.exercises ?: emptyList(),
    var currentExerciseIndex : Int = 0,

    var countDownTimer: CountDownTimer? = null,
    var currentTime : Long = exercises[currentExerciseIndex].seconds * 1000,
    var currentProgress : Float = 1.00F,
    var isPlaying : Boolean = false,
    var isPaused : Boolean = false,

    val reps : Int = exercises[currentExerciseIndex].reps,
)

object Utility {
    private const val TIME_FORMAT = "%02d:%02d"

    //convert time to milli seconds
    fun formatTime(mills : Long): String {
        return String.format(
            TIME_FORMAT,
            TimeUnit.MILLISECONDS.toMinutes(mills),
            TimeUnit.MILLISECONDS.toSeconds(mills) % 60
        )
    }
}


