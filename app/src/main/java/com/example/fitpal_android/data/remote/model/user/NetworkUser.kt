package com.example.fitpal_android.data.remote.model.user

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
    var avatarUrl: String?,
    @SerializedName("verified")
    var verified: Boolean
) {

    // Default avatar
    companion object {
        const val DEFAULT_AVATAR_URL = "https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg"
    }

    fun asModel() : User {
        return User(
            id = id,
            firstname = firstName,
            lastname = lastName,
            email = email,
            avatarUrl = avatarUrl?: DEFAULT_AVATAR_URL
        )
    }
}
