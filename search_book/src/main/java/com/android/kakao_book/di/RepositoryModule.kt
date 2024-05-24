package com.android.kakao_book.di

import com.android.kakao_book.data.repo.BookmarkRepository
import com.android.kakao_book.data.repo.BookmarkRepositoryImpl
import com.android.kakao_book.data.repo.KakaoRepository
import com.android.kakao_book.data.repo.KakaoRepositoryImpl
import com.android.kakao_book.data.source.remote.local.BookmarkLocalDataSource
import com.android.kakao_book.data.source.remote.local.BookmarkLocalDataSourceImpl
import com.android.kakao_book.data.source.remote.remote.KakaoRemoteDataSource
import com.android.kakao_book.data.source.remote.remote.KakaoRemoteDataSourceImpl
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