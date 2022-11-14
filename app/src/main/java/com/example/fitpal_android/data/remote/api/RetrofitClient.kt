package com.example.fitpal_android.data.remote.api

import android.content.Context
import com.example.fitpal_android.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitClient {

    @Volatile
    private var instance: Retrofit? = null

    private fun getInstance(context: Context) : Retrofit =
        instance ?: synchronized(this) {
            instance ?: buildRetrofit(context).also { instance = it}
        }

    private fun buildRetrofit(context: Context) : Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, ApiDateTypeAdapter())
            .create()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    fun getApiUserService(context: Context) : ApiUserService {
        return getInstance(context).create(ApiUserService::class.java)
    }

    fun getApiExerciseService(context: Context) : ApiExerciseService {
        return getInstance(context).create(ApiExerciseService::class.java)
    }


}
