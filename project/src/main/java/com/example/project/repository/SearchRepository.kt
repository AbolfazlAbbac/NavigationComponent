package com.example.project.repository

import com.example.project.api.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: ApiService) {
    suspend fun searchMovies(name: String) = api.searchMovies(name)
}