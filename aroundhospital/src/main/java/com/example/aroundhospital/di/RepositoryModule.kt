package com.example.aroundhospital.di

import com.example.aroundhospital.data.repo.BookmarkRepository
import com.example.aroundhospital.data.repo.BookmarkRepositoryImpl
import com.example.aroundhospital.data.repo.KakaoRepository
import com.example.aroundhospital.data.repo.KakaoRepositoryImpl
import com.example.aroundhospital.data.source.local.BookmarkLocalDataSource
import com.example.aroundhospital.data.source.local.BookmarkLocalDataSourceImpl
import com.example.aroundhospital.data.source.remote.KakaoRemoteDataSource
import com.example.aroundhospital.data.source.remote.KakaoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindKakaoRepository(
        kakaoRepositoryImpl: KakaoRepositoryImpl
    ): KakaoRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        bookmarkRepositoryImpl: BookmarkRepositoryImpl
    ): BookmarkRepository

    @Binds
    @Singleton
    abstract fun bindKakaoRemoteDataSource(
        kakaoRemoteDataSourceImpl: KakaoRemoteDataSourceImpl
    ): KakaoRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindBookmarkLocalDataSource(
        bookmarkLocalDataSourceImpl: BookmarkLocalDataSourceImpl
    ): BookmarkLocalDataSource
}