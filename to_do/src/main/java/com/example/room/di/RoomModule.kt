package com.example.room.di
import android.app.Application
import androidx.room.Room
import com.example.room.data.repo.SampleRepository
import com.example.room.data.repo.SampleRepositoryImpl
import com.example.room.data.source.local.SampleLocalDataSource
import com.example.room.data.source.local.SampleLocalDataSourceImpl
import com.example.room.room.SampleDao
import com.example.room.room.SampleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): SampleDatabase {
        return Room.databaseBuilder(app, SampleDatabase::class.java, "sample.db").build()
    }

    @Provides
    fun provideSampleDao(db: SampleDatabase): SampleDao = db.sampleDao()

    @Provides
    fun provideSampleLocalDataSource(sampleDao: SampleDao): SampleLocalDataSource =
        SampleLocalDataSourceImpl(sampleDao)

    @Provides
    fun provideSampleRepository(localDataSource: SampleLocalDataSource): SampleRepository =
        SampleRepositoryImpl(localDataSource)
}
