package com.example.project.di

import android.content.Context
import androidx.room.Room
import com.example.project.db.FabDatabase
import com.example.project.db.FabEntity
import com.example.project.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun dataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, FabDatabase::class.java, Utils.FAB_DATABASE
    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(db: FabDatabase) = db.fabDao()


    @Provides
    @Singleton
    fun provideEntity() = FabEntity()
}