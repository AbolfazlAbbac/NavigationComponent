package com.example.livedata.notes.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesData::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun databaseDao(): NotesDao

}