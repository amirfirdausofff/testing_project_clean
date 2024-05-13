package com.demo.testingproject.di


internal val appModule =
        remoteModule +
        repositoryModule +
        useCaseModule +
        viewModelModule
