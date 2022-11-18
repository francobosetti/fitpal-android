package com.example.fitpal_android.ui.screens.appContent.myRoutines

import com.example.fitpal_android.data.model.Routine

data class MyRoutinesState (
    val myRoutines: List<Routine> = emptyList(),
    val isFetching: Boolean = false,
    val apiMsg: Int? = null
)