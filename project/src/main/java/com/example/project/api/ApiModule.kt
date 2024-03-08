package com.example.project.api

import com.example.project.models.BodyRegister
import com.example.project.models.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiModule {

    @POST("register")
    suspend fun register(@Body bodyRegister: BodyRegister): Response<ResponseRegister>
}