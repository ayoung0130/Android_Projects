package com.example.aroundhospital.di

import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapManager
import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ManagerModule {


    @Provides
    fun kakaoMapManager(): KakaoMapManager = KakaoMapManagerImpl()
}