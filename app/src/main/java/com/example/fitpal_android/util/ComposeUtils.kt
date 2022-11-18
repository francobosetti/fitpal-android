package com.example.fitpal_android.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.fitpal_android.FitpalApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val sessionManager = FitpalApplication.sessionManager
    val settingsManager = FitpalApplication.settingsManager
    val userRepository = FitpalApplication.userRepository
    val exerciseRepository = FitpalApplication.exerciseRepository
    val routineRepository = FitpalApplication.routineRepository


    return ViewModelFactory(
        sessionManager = sessionManager,
        settingsManager = settingsManager,
        userRepository = userRepository,
        exerciseRepository = exerciseRepository,
        routineRepository = routineRepository,
        owner = LocalSavedStateRegistryOwner.current,
        defaultArgs = defaultArgs
    )
}

@Composable
fun getViewModelFactory(id: Int?, defaultArgs: Bundle? = null): ViewModelFactory {
    val sessionManager = FitpalApplication.sessionManager
    val settingsManager = FitpalApplication.settingsManager
    val userRepository = FitpalApplication.userRepository
    val exerciseRepository = FitpalApplication.exerciseRepository
    val routineRepository = FitpalApplication.routineRepository


    return ViewModelFactory(
        sessionManager = sessionManager,
        settingsManager = settingsManager,
        userRepository = userRepository,
        exerciseRepository = exerciseRepository,
        routineRepository = routineRepository,


        id = id,

        owner = LocalSavedStateRegistryOwner.current,
        defaultArgs = defaultArgs
    )
}

suspend fun resetRepositories() {
    FitpalApplication.userRepository.resetRepository()
    FitpalApplication.exerciseRepository.resetRepository()
    FitpalApplication.routineRepository.resetRepository()
}