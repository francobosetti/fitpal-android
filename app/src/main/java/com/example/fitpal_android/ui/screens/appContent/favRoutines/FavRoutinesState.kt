package com.example.fitpal_android.ui.screens.appContent.favRoutines

import com.example.fitpal_android.data.model.Routine

data class FavRoutinesState (
    val favRoutines: List<Routine> = emptyList(),
    val isFetching: Boolean = false,
    val apiMsg: Int? = null
)
