package com.example.fitpal_android.ui.screens.appContent.detailedRoutine

sealed class DetailedRoutineEvent {
    object RoutineRated : DetailedRoutineEvent()
    object FavoriteToggled : DetailedRoutineEvent()
}