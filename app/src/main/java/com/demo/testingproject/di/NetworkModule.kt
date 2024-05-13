package com.demo.testingproject.di

import com.demo.testingproject.domain.network.NetworkConfiguration
import org.koin.dsl.module

internal val networkModule = module {
    single { NetworkConfiguration.getOkHttpClient() }
    single { NetworkConfiguration.getRetrofitClient(okHttpClient = get()) }
}
