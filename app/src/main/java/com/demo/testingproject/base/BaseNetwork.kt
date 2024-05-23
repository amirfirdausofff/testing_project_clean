package com.demo.testingproject.base

import android.util.Log
import com.demo.testingproject.domain.model.general.ErrorResponse
import com.demo.testingproject.domain.model.general.FailedResult
import com.demo.testingproject.domain.model.general.ResultCall
import com.google.gson.Gson
import retrofit2.Response

suspend fun <T> networkRequest(apiCall: suspend () -> Response<T>): ResultCall<T?> {
    return networkHandling {
        val response = apiCall()

        Log.d("code_response ", response.code().toString())
        when {
            response.isSuccessful -> {
                ResultCall.Success(response.body())
            }
            response.code() in 200..299 -> {
                ResultCall.Failed(
                    FailedResult(
                        title = "Bad Request",
                        description = "The request was not formatted correctly. Please try again..."
                    )
                )
            }
            response.code() in 300..399 -> {
                ResultCall.Failed(
                    FailedResult(
                        title = "Redirection Error",
                        description = "The request was redirected. Please try again..."
                    )
                )
            }
            response.code() in 400..499 -> {
                val errorResponse = errorResponse(response)
                ResultCall.Failed(
                    FailedResult(
                        title = "Api Error",
                        description = errorResponse?.errorDescription ?: "Error"
                    )
                )
            }
            response.code() >= 500 -> {
                ResultCall.Failed(
                    FailedResult(
                        title = "Server Error",
                        description = "There was a server error. Please try again..."
                    )
                )
            }
            else -> {
                ResultCall.Failed(
                    FailedResult(
                        title = "Unknown Error",
                        description = "An unknown error occurred. Please try again..."
                    )
                )
            }
        }
    }
}

private fun <T> errorResponse(response: Response<T>): ErrorResponse? {
    val errorBodyString = response.errorBody()?.string()
    val errorResponse = if (!errorBodyString.isNullOrEmpty()) {
        try {
            Gson().fromJson(errorBodyString, ErrorResponse::class.java)
        } catch (e: Exception) {
            null
        }
    } else {
        null
    }
    return errorResponse
}

@Suppress("UNCHECKED_CAST")
fun <A, B> ResultCall<A?>.mapTo(block: (A) -> B): ResultCall<B> {
    return when (this) {
        is ResultCall.Success -> {
            val data = this.data ?: Unit as A
            ResultCall.Success(block(data))
        }
        is ResultCall.Failed -> this as ResultCall<B>
    }
}

private suspend fun <T> networkHandling(apiCall: suspend () -> ResultCall<T?>): ResultCall<T?> {
    return try {
        apiCall()
    } catch (exception: Exception) {
        ResultCall.Failed(
            FailedResult(
                title = "Failed",
                description = exception.message.orEmpty()
            )
        )
    }
}
