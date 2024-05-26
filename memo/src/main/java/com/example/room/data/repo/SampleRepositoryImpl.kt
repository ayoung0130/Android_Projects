package com.example.room.data.repo

import com.example.room.room.SampleEntity
import com.example.room.data.source.local.SampleLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(private val sampleLocalDataSource: SampleLocalDataSource) :
    SampleRepository {

    override val getAll: Flow<List<SampleEntity>>
        get() = sampleLocalDataSource.getAll

    override val delete: suspend (SampleEntity) -> Unit
        get() = sampleLocalDataSource.delete
    override val insert: suspend (SampleEntity) -> Unit
        get() = sampleLocalDataSource.insert

}