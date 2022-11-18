package com.example.fitpal_android.data.remote

import com.example.fitpal_android.data.remote.model.NetworkError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.io.IOException

abstract class RemoteDataSource {

    suspend fun <T : Any> handleApiResponse(
        execute: suspend () -> Response<T>
    ): T {
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return body
            }
            response.errorBody()?.let {
                val gson = Gson()
                val error = gson.fromJson<NetworkError>(it.string(), object : TypeToken<NetworkError?>() {}.type)
                throw DataSourceException(error.code, error.description, error.details)
            }
            throw DataSourceException(ApiErrorCodes.MISSING_ERROR, ApiErrorStrings.MISSING_ERROR_STR, null)
        } catch (e: DataSourceException) {
            throw e
        } catch (e: IOException) {
            throw DataSourceException(ApiErrorCodes.CONNECTION_ERROR, ApiErrorStrings.CONECTION_ERROR_STR, getDetailsFromException(e))
        } catch (e: Exception) {
            throw DataSourceException(ApiErrorCodes.UNEXPECTED_ERROR, ApiErrorStrings.UNEXPECTED_ERROR_STR, getDetailsFromException(e))
        }
    }

    private fun getDetailsFromException(e: Exception) : List<String> {
        return if (e.message != null) listOf(e.message!!) else emptyList()
    }
}