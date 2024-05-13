package com.demo.testingproject.domain.network

import com.demo.testingproject.BuildConfig.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConfiguration {
    private const val NETWORK_TIMEOUT = 60L

    fun getOkHttpClient(): OkHttpClient {
        val networkTimeout = NETWORK_TIMEOUT
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            readTimeout(networkTimeout, TimeUnit.SECONDS)
            writeTimeout(networkTimeout, TimeUnit.SECONDS)
            connectTimeout(networkTimeout, TimeUnit.SECONDS)
        }.build()
    }

    fun getRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(okHttpClient)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
}
