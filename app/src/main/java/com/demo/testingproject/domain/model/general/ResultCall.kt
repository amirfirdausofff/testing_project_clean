package com.demo.testingproject.domain.model.general

sealed class ResultCall<out T> {
    data class Success<T>(val data: T) : ResultCall<T>()
    data class Failed(val failedResult: FailedResult) : ResultCall<Nothing>()
}
