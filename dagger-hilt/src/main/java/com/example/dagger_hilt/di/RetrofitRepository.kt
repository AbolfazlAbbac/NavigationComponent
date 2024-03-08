package com.example.dagger_hilt.di

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RetrofitRepository @Inject constructor(private val api: ApiService) {
    fun getAllMovies() = api.getAllMovies()
}