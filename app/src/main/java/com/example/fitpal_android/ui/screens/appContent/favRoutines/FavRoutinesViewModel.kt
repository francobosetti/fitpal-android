package com.example.fitpal_android.ui.screens.appContent.favRoutines

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.DEPRECATED.Routine
import com.example.fitpal_android.data.repository.DEPRECATED.RoutineRepository

class FavRoutinesViewModel : ViewModel() {

    private var favRoutines = RoutineRepository().getFavoriteRoutines()

    fun getFavRoutines() : List<Routine> {
        return favRoutines
    }
}