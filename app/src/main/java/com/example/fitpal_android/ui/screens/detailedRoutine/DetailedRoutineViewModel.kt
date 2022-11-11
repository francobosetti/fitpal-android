package com.example.fitpal_android.ui.screens.detailedRoutine

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.Routine
import com.example.fitpal_android.data.repository.RoutineRepository

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