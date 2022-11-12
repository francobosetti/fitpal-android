package com.example.fitpal_android.data.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.fitpal_android.data.model.User
import kotlin.random.Random

class UserRepository {

    // Static variable to hold wether the user is logged in or not
    companion object {
        // TODO: ESTO ESTA MAL SEGURO
        var isLoggedIn by mutableStateOf(Random(System.currentTimeMillis()).nextBoolean())
    }

    private var user = User (
        firstname = "Faker",
        lastname = "Faker",
        email = "faker@gmail.com",
        avatarUrl = "https://pbs.twimg.com/media/Ffn_6FDX0AAe8hk?format=jpg&name=small",
        id = ULong.MAX_VALUE,
    )

    fun getCurrentUser() : User {
        return user
    }

    fun isUserLoggedIn() : Boolean {
        // TODO: implement
        return isLoggedIn
    }

    fun logIn() {
        isLoggedIn = true
    }

    fun logOut() {
        isLoggedIn = false
    }
}
