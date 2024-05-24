package com.example.room.util

import com.example.room.room.SampleDao
import com.example.room.data.repo.SampleRepository
import com.example.room.data.repo.SampleRepositoryImpl
import com.example.room.data.source.local.SampleLocalDataSource
import com.example.room.data.source.local.SampleLocalDataSourceImpl

object InjectUtil {

    fun provideSampleDao(): SampleDao {
        return RoomUtil.getSampleDao()
    }

    fun provideSampleLocalDataSource(): SampleLocalDataSource {
        return SampleLocalDataSourceImpl.getInstance(provideSampleDao())
    }

    fun provideSampleRepository(): SampleRepository {
        return SampleRepositoryImpl.getInstance(provideSampleLocalDataSource())
    }

}