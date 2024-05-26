package com.example.room.data.repo

import com.example.room.room.MemoEntity
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    val getAll: Flow<List<MemoEntity>>

    val delete: suspend (MemoEntity) -> Unit
    val insert: suspend (MemoEntity) -> Unit
}