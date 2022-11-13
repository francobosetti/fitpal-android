package com.example.fitpal_android.data.remote.model

import com.example.fitpal_android.data.model.User
import com.google.gson.annotations.SerializedName

class NetworkUser (

    @SerializedName("id")
    var id: Int,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("avatarUrl")
    var avatarUrl: String? = null,
    @SerializedName("verified")
    var verified: Boolean
) {

    fun asModel() : User {
        return User(
            id = id,
            firstname = firstName,
            lastname = lastName,
            email = email,
            avatarUrl = avatarUrl
        )
    }
}
