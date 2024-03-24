package com.example.project.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [FabEntity::class])
abstract class FabDatabase : RoomDatabase() {
    abstract fun fabDao(): FabDao
}