package com.example.fitpal_android.data.remote.api


import com.example.fitpal_android.data.remote.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiUserService {

    @POST("users")
    suspend fun registerUser(@Body registrationInfo: NetworkRegistrationInfo): Response<NetworkUser>

    @POST("users/verify_email")
    suspend fun verifyEmail(@Body verification: NetworkVerification): Response<Unit>

    @POST("users/resend_verification")
    suspend fun resendVerification(@Body email: NetworkEmail): Response<Unit>

    @POST("users/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("users/logout")
    suspend fun logout(): Response<Unit>

    @GET("users/current")
    suspend fun getCurrentUser(): Response<NetworkUser>
}