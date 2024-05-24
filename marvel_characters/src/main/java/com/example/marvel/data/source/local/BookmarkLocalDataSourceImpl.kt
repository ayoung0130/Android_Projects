package com.example.marvel.data.source.local

import com.example.marvel.room.BookmarkDao
import com.example.marvel.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkLocalDataSourceImpl @Inject constructor(private val bookmarkDao: BookmarkDao) :
    BookmarkLocalDataSource {
    override val getAll: Flow<List<BookmarkEntity>>
        get() = bookmarkDao.getAll()

    override suspend fun delete(entity: BookmarkEntity) {
        bookmarkDao.delete(entity)
    }

    override suspend fun insert(entity: BookmarkEntity) {
        bookmarkDao.insert(entity)
    }
}