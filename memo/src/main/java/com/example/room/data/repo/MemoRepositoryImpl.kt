package com.example.room.data.repo

import com.example.room.room.MemoEntity
import com.example.room.data.source.local.MemoLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(private val memoLocalDataSource: MemoLocalDataSource) :
    MemoRepository {

    override val getAll: Flow<List<MemoEntity>>
        get() = memoLocalDataSource.getAll

    override val delete: suspend (MemoEntity) -> Unit
        get() = memoLocalDataSource.delete
    override val insert: suspend (MemoEntity) -> Unit
        get() = memoLocalDataSource.insert

}