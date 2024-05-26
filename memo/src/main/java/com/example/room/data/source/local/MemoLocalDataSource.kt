package com.example.room.data.source.local

import com.example.room.room.MemoEntity
import kotlinx.coroutines.flow.Flow

interface MemoLocalDataSource {
    val getAll: Flow<List<MemoEntity>>

    val delete: suspend (MemoEntity) -> Unit
    val insert: suspend (MemoEntity) -> Unit
}