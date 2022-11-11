package com.example.fitpal_android.ui.screens.favRoutines

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.Routine
import com.example.fitpal_android.data.repository.RoutineRepository

class FavRoutinesViewModel : ViewModel() {

    private var favRoutines = RoutineRepository().getFavoriteRoutines()

    fun getFavRoutines() : List<Routine> {
        return favRoutines
    }
}