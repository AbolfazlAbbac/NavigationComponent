package com.example.dagger_hilt.simple.di

import android.content.Context
import com.example.dagger_hilt.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltInjection {

    @Provides
    @Singleton
    @Named("abolfazl")
    fun provideUserName(): String = "abolfazlabbasi185"

    @Provides
    @Named("fatemeh")
    fun provideUserName2(): String = "FatemehShahMohammad"

    @Provides
    @Named("context")
    fun provideName(@ApplicationContext context: Context): String {
        return context.getString(R.string.app_name)
    }
}