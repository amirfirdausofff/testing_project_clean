package com.demo.testingproject.domain.usecase

import com.demo.testingproject.domain.model.general.ResultCall
import com.demo.testingproject.domain.model.request.RegisterRequest
import com.demo.testingproject.domain.model.response.RegisterResponse
import com.demo.testingproject.domain.repository.RegisterRepository

class RegisterUseCase(val repository: RegisterRepository) {
    suspend fun invoke(request: RegisterRequest): ResultCall<RegisterResponse> {
        return repository.register(request)
    }
}
