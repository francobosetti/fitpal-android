package com.example.fitpal_android

import android.app.Application
import com.example.fitpal_android.data.remote.UserRemoteDataSource
import com.example.fitpal_android.data.remote.api.RetrofitClient
import com.example.fitpal_android.data.repository.UserRepository
import com.example.fitpal_android.util.SessionManager


class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getApiUserService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

}