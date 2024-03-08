package com.example.project.di

import com.example.project.utils.Utils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiService {

    @Provides
    @Singleton
    private fun baseUrl() = Utils.BASE_URL

    @Provides
    @Singleton
    private fun gsonConverter(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    private fun clientTime() = Utils.CLIENT_TIME

    @Provides
    @Singleton
    private fun interceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    private fun clientHttp(interceptor: HttpLoggingInterceptor, clientTime: Long) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(clientTime, TimeUnit.SECONDS)
            .readTimeout(clientTime, TimeUnit.SECONDS)
            .connectTimeout(clientTime, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun apiServiceRetrofit(client: OkHttpClient, gson: Gson, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
}