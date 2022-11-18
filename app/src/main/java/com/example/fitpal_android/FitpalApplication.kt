package com.example.fitpal_android

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.example.fitpal_android.data.remote.ExerciseRemoteDataSource
import com.example.fitpal_android.data.remote.RoutineRemoteDataSource
import com.example.fitpal_android.data.remote.UserRemoteDataSource
import com.example.fitpal_android.data.remote.api.RetrofitClient
import com.example.fitpal_android.data.repository.ExerciseRepository
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.util.SessionManager
import com.example.fitpal_android.util.SettingsManager


class FitpalApplication : Application() {

    // Initialize repositories when application starts
    override fun onCreate() {
        super.onCreate()

        sessionManager = SessionManager(this)
        settingsManager = SettingsManager(this)

        userRemoteDataSource = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))
        exerciseRemoteDataSource = ExerciseRemoteDataSource(RetrofitClient.getApiExerciseService(this))
        routineRemoteDataSource = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))


        userRepository = UserRepository(userRemoteDataSource, sessionManager)
        exerciseRepository = ExerciseRepository(ExerciseRemoteDataSource(RetrofitClient.getApiExerciseService(this)))
        routineRepository = RoutineRepository(RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this)), exerciseRemoteDataSource, userRemoteDataSource)
    }

    // Access repositories from anywhere in the app
    companion object {
        // -------- OTHERS --------
        lateinit var sessionManager: SessionManager
        lateinit var settingsManager: SettingsManager

        // -------- REPOSITORIES --------
        lateinit var userRepository: UserRepository
        lateinit var exerciseRepository: ExerciseRepository
        lateinit var routineRepository: RoutineRepository

        // -------- REMOTE DATA SOURCES --------
        private lateinit var userRemoteDataSource: UserRemoteDataSource
        private lateinit var exerciseRemoteDataSource: ExerciseRemoteDataSource
        private lateinit var routineRemoteDataSource: RoutineRemoteDataSource
    }
}