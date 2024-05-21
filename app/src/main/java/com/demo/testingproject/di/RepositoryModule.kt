package com.demo.testingproject.di

import com.demo.testingproject.domain.repository.LoginRepository
import com.demo.testingproject.domain.repository.LoginRepositoryImpl
import com.demo.testingproject.domain.repository.RegisterRepository
import com.demo.testingproject.domain.repository.RegisterRepositoryImpl
import org.koin.dsl.module

internal val repositoryModule = module {
    factory<LoginRepository> { LoginRepositoryImpl(get()) }
    factory<RegisterRepository> { RegisterRepositoryImpl(get()) }
}
