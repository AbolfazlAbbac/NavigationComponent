package com.example.project.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project.utils.Utils

@Dao
interface FabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieFab(entity: FabEntity)

    @Delete
    suspend fun deleteMovie(entity: FabEntity)

    @Query("SELECT * FROM ${Utils.FAB_TABLE}")
    fun getAllFabMovies(): MutableList<FabEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Utils.FAB_TABLE} WHERE id = :id)")
    suspend fun movieExistsOnFab(id: Int): Boolean
}