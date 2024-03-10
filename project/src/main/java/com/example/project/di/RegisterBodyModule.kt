package com.example.project.di

import com.example.project.models.BodyRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RegisterBodyModule {

    @Provides
    @Singleton
    fun provideRegisterBody() = BodyRegister()
}