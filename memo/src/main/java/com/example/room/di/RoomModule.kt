package com.example.room.di

import android.app.Application
import androidx.room.Room
import com.example.room.room.MemoDao
import com.example.room.room.MemoDatabase
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
    fun provideDatabase(app: Application): MemoDatabase {
        return Room.databaseBuilder(app, MemoDatabase::class.java, "sample.db").build()
    }

    @Provides
    fun provideSampleDao(db: MemoDatabase): MemoDao = db.sampleDao()
}
