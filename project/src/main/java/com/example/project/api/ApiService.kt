package com.example.project.api

import com.example.project.models.BodyRegister
import com.example.project.models.ResponseGenres
import com.example.project.models.ResponseMovieList
import com.example.project.models.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("register")
    suspend fun register(@Body bodyRegister: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun getTopMovies(@Path("genre_id") id: Int): Response<ResponseMovieList>

    @GET("genres")
    suspend fun getAllGenres(): Response<ResponseGenres>

    @GET("movies")
    suspend fun getLastMovies(): Response<ResponseMovieList>
}