package com.example.room.data.repo

import com.example.room.room.SampleEntity
import com.example.room.data.source.local.SampleLocalDataSource

class SampleRepositoryImpl(private val sampleLocalDataSource: SampleLocalDataSource) :
    SampleRepository {
    override fun getAll(): List<SampleEntity> =
        sampleLocalDataSource.getAll()

    override fun delete(entity: SampleEntity) {
        sampleLocalDataSource.delete(entity)
    }

    override fun insert(entity: SampleEntity) {
        sampleLocalDataSource.insert(entity)
    }
    companion object {
        private var INSTANCE: SampleRepository? = null

        fun getInstance(sampleLocalDataSource: SampleLocalDataSource): SampleRepository =
            INSTANCE ?: SampleRepositoryImpl(sampleLocalDataSource).apply { INSTANCE = this }
    }
}