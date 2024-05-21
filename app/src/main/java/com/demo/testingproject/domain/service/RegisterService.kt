package com.demo.testingproject.domain.service

import com.demo.testingproject.domain.model.request.RegisterRequest
import com.demo.testingproject.domain.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("users")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
