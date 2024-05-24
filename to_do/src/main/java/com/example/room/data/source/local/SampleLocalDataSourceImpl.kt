package com.example.room.data.source.local

import com.example.room.room.SampleDao
import com.example.room.room.SampleEntity

class SampleLocalDataSourceImpl(private val sampleDao: SampleDao) : SampleLocalDataSource {
    override fun getAll(): List<SampleEntity> =
        sampleDao.getAll()

    override fun delete(entity: SampleEntity) {
        sampleDao.delete(entity)
    }

    override fun insert(entity: SampleEntity) {
        sampleDao.insert(entity)
    }


    companion object {
        private var INSTANCE: SampleLocalDataSource? = null

        fun getInstance(sampleDao: SampleDao): SampleLocalDataSource =
            INSTANCE ?: SampleLocalDataSourceImpl(sampleDao).apply { INSTANCE = this }
    }

}