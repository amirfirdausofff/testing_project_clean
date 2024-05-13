package com.demo.testingproject.domain.service

import com.demo.testingproject.domain.model.request.LoginRequest
import com.demo.testingproject.domain.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("authentication_token")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
