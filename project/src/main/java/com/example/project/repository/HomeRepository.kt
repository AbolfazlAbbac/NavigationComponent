package com.example.project.repository

import com.example.project.api.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiService) {
    suspend fun getTopMovies(id: Int) = api.getTopMovies(id)
    suspend fun getAllGenres() = api.getAllGenres()

    suspend fun getLastMovies() = api.getLastMovies()
}