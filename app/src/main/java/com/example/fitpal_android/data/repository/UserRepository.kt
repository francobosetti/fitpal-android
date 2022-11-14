package com.example.fitpal_android.data.repository


import com.example.fitpal_android.data.model.User
import com.example.fitpal_android.data.remote.UserRemoteDataSource
import com.example.fitpal_android.util.SessionManager
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource,
    private val sessionManager: SessionManager
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user got from the network.
    private var currentUser: User? = null

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }

    suspend fun fetchUser(): Unit {
        currentUserMutex.withLock {
            currentUser = remoteDataSource.getCurrentUser().asModel()
        }
    }

    suspend fun getCurrentUser() : User? {
        if (currentUser == null) {
            fetchUser()
        }

        return currentUserMutex.withLock { this.currentUser }
    }

    fun isLoggedIn() : Boolean {
        return sessionManager.loadAuthToken() != null
    }

    suspend fun registerUser(email: String, password: String, firstname: String, lastname: String) : User {
        return remoteDataSource.registerUser(email, password, firstname, lastname).asModel()
    }

    suspend fun verifyEmail(email: String, code: String) {
        remoteDataSource.verifyEmail(email, code)
    }

    suspend fun resendVerification(email: String) {
        remoteDataSource.resendVerification(email)
    }

    suspend fun updateUser(firstname: String, lastname: String, avatarUrl: String) {
        currentUserMutex.withLock {
            currentUser = remoteDataSource.updateUser(firstname, lastname, avatarUrl).asModel()
        }
    }
}