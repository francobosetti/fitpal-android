package com.example.fitpal_android.ui.screens.appContent.exploreRoutines

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.Routine
import com.example.fitpal_android.data.repository.RoutineRepository

class ExploreRoutinesViewModel : ViewModel() {

    private var otherRoutines = RoutineRepository().getOtherRoutines()

    fun getOtherRoutines() : List<Routine> {
        return otherRoutines
    }
}