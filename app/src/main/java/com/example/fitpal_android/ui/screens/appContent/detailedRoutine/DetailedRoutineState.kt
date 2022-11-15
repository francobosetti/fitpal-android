package com.example.fitpal_android.ui.screens.appContent.detailedRoutine

import com.example.fitpal_android.data.model.Routine


data class DetailedRoutineState (
    val routine: Routine? = null,
    val userRating: Double? = null,
    val isFetching: Boolean = false,
    val error: String = "",
)