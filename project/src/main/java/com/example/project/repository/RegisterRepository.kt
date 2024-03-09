package com.example.project.repository

import com.example.project.api.ApiService
import com.example.project.models.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun registerUser(bodyRegister: BodyRegister) = apiService.register(bodyRegister)
}