package com.example.room.data.source.local

import com.example.room.room.SampleEntity
import kotlinx.coroutines.flow.Flow

interface SampleLocalDataSource {
    val getAll: Flow<List<SampleEntity>>

    val delete: suspend (SampleEntity) -> Unit
    val insert: suspend (SampleEntity) -> Unit
}