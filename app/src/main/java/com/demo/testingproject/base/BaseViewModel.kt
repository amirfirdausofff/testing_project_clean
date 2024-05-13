package com.demo.testingproject.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }

    suspend fun apiDelay(timeInMillis: Long) {
        delay(timeInMillis)
    }
}
