package com.example.project.repository

import com.example.project.db.FabDao
import javax.inject.Inject


class FavoriteRepository @Inject constructor(private val db: FabDao) {

    suspend fun getAllFavorites() = db.getAllFabMovies()
}