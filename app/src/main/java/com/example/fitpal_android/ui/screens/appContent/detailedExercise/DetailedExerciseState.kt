package com.example.fitpal_android.ui.screens.appContent.detailedExercise

import com.example.fitpal_android.data.model.Exercise

data class DetailedExerciseState (
    val exercise: Exercise? = null,
    val isFetching: Boolean = false,
    val error: String = "",
)