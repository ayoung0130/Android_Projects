package com.example.room.data.repo

import com.example.room.room.SampleEntity
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    val getAll: Flow<List<SampleEntity>>

    val delete: suspend (SampleEntity) -> Unit
    val insert: suspend (SampleEntity) -> Unit
}