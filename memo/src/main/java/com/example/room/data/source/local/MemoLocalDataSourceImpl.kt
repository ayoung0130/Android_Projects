package com.example.room.data.source.local

import com.example.room.room.MemoDao
import com.example.room.room.MemoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemoLocalDataSourceImpl @Inject constructor(private val memoDao: MemoDao) :
    MemoLocalDataSource {
    override val getAll: Flow<List<MemoEntity>>
        get() = memoDao.getAll()
    override val delete: suspend (MemoEntity) -> Unit
        get() = { memoDao.delete(it) }
    override val insert: suspend (MemoEntity) -> Unit
        get() = { memoDao.insert(it) }
}