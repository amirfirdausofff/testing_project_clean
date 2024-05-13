package com.demo.testingproject.domain.repository

import com.demo.testingproject.base.mapTo
import com.demo.testingproject.base.networkRequest
import com.demo.testingproject.domain.model.general.ResultCall
import com.demo.testingproject.domain.model.request.LoginRequest
import com.demo.testingproject.domain.model.response.LoginResponse
import com.demo.testingproject.domain.service.AuthService


interface LoginRepository {
    suspend fun login(request: LoginRequest): ResultCall<LoginResponse>
}

class LoginRepositoryImpl(private val service: AuthService) : LoginRepository {
    override suspend fun login(request: LoginRequest): ResultCall<LoginResponse> {
        return networkRequest { service.login(request = request) }.mapTo { it }
    }
}
