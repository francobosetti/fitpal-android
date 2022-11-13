package com.example.fitpal_android.data.repository


import com.example.fitpal_android.data.model.User
import com.example.fitpal_android.data.remote.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
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
}