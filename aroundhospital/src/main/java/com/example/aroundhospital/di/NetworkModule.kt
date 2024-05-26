package com.example.aroundhospital.di

import com.example.aroundhospital.network.KakaoService
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
    fun provideKakaoService(
        gsonConverterFactory: GsonConverterFactory
    ): KakaoService =
        Retrofit.Builder().baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(KakaoService::class.java)
}