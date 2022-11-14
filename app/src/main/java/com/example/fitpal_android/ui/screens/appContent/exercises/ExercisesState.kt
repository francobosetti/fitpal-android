package com.example.fitpal_android.ui.screens.appContent.exercises

import com.example.fitpal_android.data.model.Exercise

data class ExercisesState (
    val exercises: List<Exercise> = emptyList(),
    val isFetching: Boolean = false,
    val error: String = ""
)