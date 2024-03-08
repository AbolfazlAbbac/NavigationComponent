package com.example.dagger_hilt.di

import com.example.dagger_hilt.MoviesModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movies")
    fun getAllMovies(): Call<MoviesModel>
}