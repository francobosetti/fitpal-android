package com.example.fitpal_android.data.remote

import com.example.fitpal_android.data.remote.api.ApiUserService
import com.example.fitpal_android.data.remote.model.user.*
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

    suspend fun getCurrentUser(): NetworkUser {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }

    suspend fun updateUser(firstname: String, lastname: String, avatarUrl: String): NetworkUser {
        return handleApiResponse {
            apiUserService.updateUser(
                NetworkModifyUserInfo(firstname, lastname, avatarUrl)
            )
        }
    }

    suspend fun registerUser(
        email: String,
        password: String,
        firstname: String,
        lastname: String
    ): NetworkUser {
        return handleApiResponse {
            apiUserService.registerUser(
                NetworkRegistrationInfo(
                    password = password,
                    email = email,
                    firstName = firstname,
                    lastName = lastname
                )
            )
        }
    }

    suspend fun verifyEmail(email: String, code: String) {
        handleApiResponse {
            apiUserService.verifyEmail(
                NetworkVerification(
                    email = email,
                    code = code
                )
            )
        }
    }

    suspend fun resendVerification(email: String) {
        handleApiResponse { apiUserService.resendVerification(NetworkEmail(email)) }
    }
}