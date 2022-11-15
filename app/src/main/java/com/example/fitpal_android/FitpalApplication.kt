package com.example.fitpal_android

import android.app.Application
import com.example.fitpal_android.data.remote.ExerciseRemoteDataSource
import com.example.fitpal_android.data.remote.RoutineRemoteDataSource
import com.example.fitpal_android.data.remote.UserRemoteDataSource
import com.example.fitpal_android.data.remote.api.RetrofitClient
import com.example.fitpal_android.data.repository.ExerciseRepository
import com.example.fitpal_android.data.repository.RoutineRepository
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.util.SessionManager


class FitpalApplication : Application() {

    // -------- REMOTE DATA SOURCES --------
    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    private val exerciseRemoteDataSource: ExerciseRemoteDataSource
        get() = ExerciseRemoteDataSource(RetrofitClient.getApiExerciseService(this))

    private val routineRemoteDataSource: RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    // -------- REPOSITORIES --------
    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource, sessionManager)

    val exerciseRepository: ExerciseRepository
        get() = ExerciseRepository(exerciseRemoteDataSource)

    val routineRepository: RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource, exerciseRemoteDataSource)


    // -------- OTHERS --------
    val sessionManager: SessionManager
        get() = SessionManager(this)

}