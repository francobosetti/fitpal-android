package com.example.fitpal_android.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.fitpal_android.FitpalApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as FitpalApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val exerciseRepository = application.exerciseRepository


    return ViewModelFactory(
        sessionManager = sessionManager,
        userRepository = userRepository,
        exerciseRepository = exerciseRepository,
        owner = LocalSavedStateRegistryOwner.current,
        defaultArgs = defaultArgs
    )
}

@Composable
fun getViewModelFactory(exerciseId: Int?, defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as FitpalApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val exerciseRepository = application.exerciseRepository


    return ViewModelFactory(
        sessionManager = sessionManager,
        userRepository = userRepository,
        exerciseRepository = exerciseRepository,

        exerciseId = exerciseId,

        owner = LocalSavedStateRegistryOwner.current,
        defaultArgs = defaultArgs
    )
}