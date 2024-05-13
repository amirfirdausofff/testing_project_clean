package com.demo.testingproject.di

import com.demo.testingproject.domain.service.AuthService
import com.demo.testingproject.util.createService
import org.koin.dsl.module

internal val remoteModule = networkModule + module {
    factory { createService<AuthService>(get()) }
}
