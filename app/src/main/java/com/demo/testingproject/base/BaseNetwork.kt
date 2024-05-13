package com.demo.testingproject.base

import com.demo.testingproject.domain.model.general.FailedResult
import com.demo.testingproject.domain.model.general.ResultCall
import retrofit2.Response

suspend fun <T> networkRequest(apiCall: suspend () -> Response<T>): ResultCall<T?> {
    return networkHandling {
        val response = apiCall()
        if (response.isSuccessful) {
            ResultCall.Success(response.body())
        } else {
            ResultCall.Failed(
                FailedResult(
                    title = "Failed",
                    description = "Please try again..."
                )
            )
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <A, B> ResultCall<A?>.mapTo(block: (A) -> B): ResultCall<B> {
    return when (this) {
        is ResultCall.Success -> {
            val data = this.data ?: Unit as A
            ResultCall.Success(block(data))
        }

        is ResultCall.Failed ->
            ResultCall.Failed(
                FailedResult(
                    title = "Failed",
                    description = "Please try again..."
                )
            )
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
