package com.example.room.di

import com.example.room.data.repo.SampleRepository
import com.example.room.data.repo.SampleRepositoryImpl
import com.example.room.data.source.local.SampleLocalDataSource
import com.example.room.data.source.local.SampleLocalDataSourceImpl
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
        sampleRepositoryImpl: SampleRepositoryImpl
    ): SampleRepository
    @Binds
    @Singleton
    abstract fun bindSampleLocalDataSource(
        sampleLocalDataSourceImpl: SampleLocalDataSourceImpl
    ): SampleLocalDataSource

}