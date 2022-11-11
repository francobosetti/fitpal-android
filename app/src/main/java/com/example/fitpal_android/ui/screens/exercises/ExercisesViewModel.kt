package com.example.fitpal_android.ui.screens.exercises

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.Exercise
import com.example.fitpal_android.data.repository.ExerciseRepository

class ExercisesViewModel : ViewModel() {

    private var exercises = ExerciseRepository().getExercises()


    fun getExercises() : List<Exercise> {
        return exercises
    }
}