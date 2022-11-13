package com.example.fitpal_android.ui.screens.appContent.myRoutines

import androidx.lifecycle.ViewModel
import com.example.fitpal_android.data.repository.DEPRECATED.Routine
import com.example.fitpal_android.data.repository.DEPRECATED.RoutineRepository

class MyRoutinesViewModel : ViewModel() {

    private var myRoutines = RoutineRepository().getMyRoutines()

    fun getMyRoutines() : List<Routine> {
        return myRoutines
    }
}