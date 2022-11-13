package com.example.fitpal_android.data.remote

import com.example.fitpal_android.data.remote.api.ApiUserService
import com.example.fitpal_android.data.remote.model.NetworkCredentials
import com.example.fitpal_android.data.remote.model.NetworkUser
import com.example.fitpal_android.util.SessionManager

class UserRemoteDataSource(
    private val sessionManager: SessionManager,
    private val apiUserService: ApiUserService
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            apiUserService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { apiUserService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser() : NetworkUser {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }
}