package com.example.project.di

import com.example.project.api.ApiService
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
class ApiModule {

    @Provides
    @Singleton
     fun baseUrl() = Utils.BASE_URL

    @Provides
    @Singleton
     fun gsonConverter(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
     fun clientTime() = Utils.CLIENT_TIME

    @Provides
    @Singleton
     fun interceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
     fun clientHttp(interceptor: HttpLoggingInterceptor, clientTime: Long) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(clientTime, TimeUnit.SECONDS)
            .readTimeout(clientTime, TimeUnit.SECONDS)
            .connectTimeout(clientTime, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun apiServiceRetrofit(client: OkHttpClient, gson: Gson, baseUrl: String): ApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(ApiService::class.java)
}