package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import com.example.fitpal_android.data.model.Routine

data class ExploreRoutinesState (
    val otherRoutines: List<Routine> = emptyList(),
    val isFetching: Boolean = false,
    val apiMsg: Int? = null
)