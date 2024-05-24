package com.example.marvel.di

import com.example.marvel.data.repo.BookmarkRepository
import com.example.marvel.data.repo.BookmarkRepositoryImpl
import com.example.marvel.data.repo.MarvelRepository
import com.example.marvel.data.repo.MarvelRepositoryImpl
import com.example.marvel.data.source.local.BookmarkLocalDataSource
import com.example.marvel.data.source.local.BookmarkLocalDataSourceImpl
import com.example.marvel.data.source.remote.MarvelRemoteDataSource
import com.example.marvel.data.source.remote.MarvelRemoteDataSourceImpl
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
    abstract fun bindMarvelRepository(
        marvelRepositoryImpl: MarvelRepositoryImpl
    ): MarvelRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        bookmarkRepositoryImpl: BookmarkRepositoryImpl
    ): BookmarkRepository

    @Binds
    @Singleton
    abstract fun bindMarvelRemoteDataSource(
        marvelRemoteDataSourceImpl: MarvelRemoteDataSourceImpl
    ): MarvelRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindBookmarkLocalDataSource(
        bookmarkLocalDataSourceImpl: BookmarkLocalDataSourceImpl
    ): BookmarkLocalDataSource
}