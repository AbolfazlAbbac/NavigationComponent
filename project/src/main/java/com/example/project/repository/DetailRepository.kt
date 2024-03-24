package com.example.project.repository

import com.example.project.api.ApiService
import com.example.project.db.FabDao
import com.example.project.db.FabDatabase
import com.example.project.db.FabEntity
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val api: ApiService, private val dao: FabDao
) {

    //Api
    suspend fun getDetail(id: Int) = api.getMovieDetail(id)

    //Database
    suspend fun insertMovie(fabEntity: FabEntity) = dao.addMovieFab(fabEntity)
    suspend fun deleteMovie(fabEntity: FabEntity) = dao.deleteMovie(fabEntity)
    suspend fun existMovie(id: Int) = dao.movieExistsOnFab(id)
}