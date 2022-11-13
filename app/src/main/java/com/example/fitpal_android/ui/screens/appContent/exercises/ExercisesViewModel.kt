package com.example.fitpal_android.ui.screens.appContent.exercises

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.DEPRECATED.Exercise
import com.example.fitpal_android.data.repository.DEPRECATED.ExerciseRepository

class ExercisesViewModel : ViewModel() {

    private var exercises = ExerciseRepository().getExercises()


    fun getExercises() : List<Exercise> {
        return exercises
    }
}