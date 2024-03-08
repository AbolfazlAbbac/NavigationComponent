package com.example.dagger_hilt.di

import com.example.BASE_URL
import com.example.BODY_NAMED
import com.example.HEADERS_NAMED
import com.example.TIMEOUT_NETWORK
import com.example.dagger_hilt.MoviesModel
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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideTimeOutOfOkHttp() = TIMEOUT_NETWORK

    @Provides
    @Singleton
    fun provideBaseURL() = BASE_URL

    @Provides
    @Singleton
    @Named(HEADERS_NAMED)
    fun provideInterceptorLoggingHeader(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    @Singleton
    @Named(BODY_NAMED)
    fun provideInterceptorLoggingBody() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideClientOkHttp(
        time: Long,
        @Named(BODY_NAMED) body: HttpLoggingInterceptor,
        @Named(HEADERS_NAMED) headers: HttpLoggingInterceptor
    ) =
        OkHttpClient.Builder()
            .addInterceptor(body)
            .addInterceptor(headers)
            .writeTimeout(time, TimeUnit.SECONDS)
            .readTimeout(time, TimeUnit.SECONDS)
            .connectTimeout(time, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()


    @Provides
    @Singleton
    fun gsonConverter(): Gson = GsonBuilder().setLenient().create()


    @Provides
    @Singleton
    fun provideRetrofit(baseURl: String, gson: Gson, client: OkHttpClient): ApiService =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseURl)
            .build()
            .create(ApiService::class.java)


}