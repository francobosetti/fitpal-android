package com.example.fitpal_android.data.remote.model.user

import com.google.gson.annotations.SerializedName

class NetworkRegistrationInfo(
    @SerializedName("password")
    var password: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("username")
    var username: String = email,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("avatarUrl")
    var avatarUrl: String = DEFAULT_AVATAR_URL,
) {
    // Default avatar
    companion object {
        const val DEFAULT_AVATAR_URL = "https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg"
    }
}