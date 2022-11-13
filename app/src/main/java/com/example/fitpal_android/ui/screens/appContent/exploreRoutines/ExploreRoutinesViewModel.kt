package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.DEPRECATED.Routine
import com.example.fitpal_android.data.repository.DEPRECATED.RoutineRepository

class ExploreRoutinesViewModel : ViewModel() {

    private var otherRoutines = RoutineRepository().getOtherRoutines()

    fun getOtherRoutines() : List<Routine> {
        return otherRoutines
    }
}