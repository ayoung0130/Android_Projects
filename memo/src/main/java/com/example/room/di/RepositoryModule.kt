package com.example.room.di

import com.example.room.data.repo.MemoRepository
import com.example.room.data.repo.MemoRepositoryImpl
import com.example.room.data.source.local.MemoLocalDataSource
import com.example.room.data.source.local.MemoLocalDataSourceImpl
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
    abstract fun bindSampleRepository(
        memoRepositoryImpl: MemoRepositoryImpl
    ): MemoRepository
    @Binds
    @Singleton
    abstract fun bindSampleLocalDataSource(
        sampleLocalDataSourceImpl: MemoLocalDataSourceImpl
    ): MemoLocalDataSource

}