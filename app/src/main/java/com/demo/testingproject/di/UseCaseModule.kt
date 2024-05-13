package com.demo.testingproject.di

import com.demo.testingproject.domain.usecase.LoginUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { LoginUseCase(get()) }
}
