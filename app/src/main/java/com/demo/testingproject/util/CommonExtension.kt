package com.demo.testingproject.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import retrofit2.Retrofit

inline fun silence(body: () -> Unit) {
    try {
        body()
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
    }
}

inline fun <reified SERVICE> createService(retrofit: Retrofit): SERVICE {
    return retrofit.create(SERVICE::class.java)
}

inline fun <T> LifecycleOwner.subscribeSingleState(
    liveData: LiveData<StateWrapper<T>>,
    crossinline onEventUnhandled: (T) -> Unit
) {
    liveData.observe(this) {
        it?.getEventIfNotHandled()?.let(onEventUnhandled)
    }
}
