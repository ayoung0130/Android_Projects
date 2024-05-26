package com.example.aroundhospital.data.source.local

import com.example.aroundhospital.room.BookmarkDao
import com.example.aroundhospital.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkLocalDataSourceImpl @Inject constructor(private val bookmarkDao: BookmarkDao) :
    BookmarkLocalDataSource {
    override val getAll: Flow<List<BookmarkEntity>>
        get() = bookmarkDao.getAll()
    override val delete: suspend (BookmarkEntity) -> Unit
        get() = { bookmarkDao.delete(it) }
    override val insert: suspend (BookmarkEntity) -> Unit
        get() = { bookmarkDao.insert(it) }

}