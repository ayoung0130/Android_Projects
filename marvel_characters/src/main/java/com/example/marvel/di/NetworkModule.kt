package com.example.marvel.di

import com.example.marvel.network.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideMarvelService(
        gsonConverterFactory: GsonConverterFactory
    ): MarvelService =
        // 안드로이드 9 이상에서는 https 사용해야 함
        Retrofit.Builder().baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(MarvelService::class.java)
}