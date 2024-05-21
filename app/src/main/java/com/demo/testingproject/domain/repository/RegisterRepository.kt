package com.demo.testingproject.domain.repository

import com.demo.testingproject.base.mapTo
import com.demo.testingproject.base.networkRequest
import com.demo.testingproject.domain.model.general.ResultCall
import com.demo.testingproject.domain.model.request.RegisterRequest
import com.demo.testingproject.domain.model.response.RegisterResponse
import com.demo.testingproject.domain.service.RegisterService


interface RegisterRepository {
    suspend fun register(request: RegisterRequest): ResultCall<RegisterResponse>
}

class RegisterRepositoryImpl(private val service: RegisterService) : RegisterRepository {
    override suspend fun register(request: RegisterRequest): ResultCall<RegisterResponse> {
        return networkRequest { service.register(request = request) }.mapTo { it }
    }
}
