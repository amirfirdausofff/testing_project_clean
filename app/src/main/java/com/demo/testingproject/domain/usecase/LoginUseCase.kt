package com.demo.testingproject.domain.usecase

import com.demo.testingproject.domain.model.general.ResultCall
import com.demo.testingproject.domain.model.request.LoginRequest
import com.demo.testingproject.domain.model.response.LoginResponse
import com.demo.testingproject.domain.repository.LoginRepository

class LoginUseCase(val repository: LoginRepository) {
    suspend fun invoke(request: LoginRequest): ResultCall<LoginResponse> {
        return repository.login(request)
    }
}
