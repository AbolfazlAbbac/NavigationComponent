package com.example.dagger_hilt.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(exportSchema = false, entities = [NoteModel::class], version = 1)
abstract class DataBaseNote() : RoomDatabase() {
    abstract fun databaseDao(): Dao
}