package com.example.fitpal_android.ui.screens.appContent.detailedRoutine

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.DEPRECATED.Routine
import com.example.fitpal_android.data.repository.DEPRECATED.RoutineRepository

class DetailedRoutineViewModel : ViewModel() {
    private val defaultId = 1
    private var started = false

    private var routine = RoutineRepository().getRoutineById(defaultId)

    fun initialize(routineId: Int?) {
        if (routineId != null && !started) {
            started = true
            routine = RoutineRepository().getRoutineById(routineId)
        }
    }

    fun getRoutine() : Routine {
        return routine
    }
}