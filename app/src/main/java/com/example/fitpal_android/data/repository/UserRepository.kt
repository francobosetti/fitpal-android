package com.example.fitpal_android.data.repository

import com.example.fitpal_android.data.model.User

class UserRepository {

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
}