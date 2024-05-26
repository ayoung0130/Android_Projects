package com.example.room.data.source.local

import com.example.room.room.SampleDao
import com.example.room.room.SampleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SampleLocalDataSourceImpl @Inject constructor(private val sampleDao: SampleDao) :
    SampleLocalDataSource {
    override val getAll: Flow<List<SampleEntity>>
        get() = sampleDao.getAll()
    override val delete: suspend (SampleEntity) -> Unit
        get() = { sampleDao.delete(it) }
    override val insert: suspend (SampleEntity) -> Unit
        get() = { sampleDao.insert(it) }
}