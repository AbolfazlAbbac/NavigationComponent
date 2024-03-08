package com.example.dagger_hilt.di

import android.content.Context
import androidx.room.Room
import com.example.NAME_DATABASE
import com.example.dagger_hilt.db.DataBaseNote
import com.example.dagger_hilt.db.NoteModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DataBaseNote::class.java, NAME_DATABASE)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideDao(db: DataBaseNote) = db.databaseDao()

    @Provides
    @Singleton
    fun provideNoteModel() = NoteModel()
}