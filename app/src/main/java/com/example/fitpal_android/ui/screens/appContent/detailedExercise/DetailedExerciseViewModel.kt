package com.example.fitpal_android.ui.screens.appContent.detailedExercise

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.DEPRECATED.Exercise
import com.example.fitpal_android.data.repository.DEPRECATED.ExerciseRepository

class DetailedExerciseViewModel : ViewModel() {
    private val defaultId = 1
    private var started = false

    private var exercise = ExerciseRepository().getExerciseById(defaultId)

    fun initialize(exerciseId: Int?) {
        if (exerciseId != null && !started) {
            started = true
            exercise = ExerciseRepository().getExerciseById(exerciseId)
        }
    }

    fun getExercise() : Exercise {
        return exercise
    }
}