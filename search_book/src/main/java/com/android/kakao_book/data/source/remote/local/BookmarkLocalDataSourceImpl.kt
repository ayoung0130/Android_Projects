package com.android.kakao_book.data.source.remote.local

import com.android.kakao_book.room.bookmark.BookmarkDao
import com.android.kakao_book.room.bookmark.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkLocalDataSourceImpl @Inject constructor(private val bookmarkDao: BookmarkDao) : BookmarkLocalDataSource {

    override val getAll: Flow<List<BookmarkEntity>>
        get() =  bookmarkDao.getAll()

    override suspend fun delete(entity: BookmarkEntity) {
        bookmarkDao.delete(entity)
    }

    override suspend fun insert(entity: BookmarkEntity) {
        bookmarkDao.insert(entity)
    }
}