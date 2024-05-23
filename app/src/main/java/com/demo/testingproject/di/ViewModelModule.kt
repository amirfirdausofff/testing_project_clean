package com.demo.testingproject.di

import com.demo.testingproject.view.login.LoginViewModel
import com.demo.testingproject.view.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel {
        LoginViewModel(
            loginUseCase = get()
        )
    }

    viewModel {
        RegisterViewModel(
            registerUseCase = get()
        )
    }

}
