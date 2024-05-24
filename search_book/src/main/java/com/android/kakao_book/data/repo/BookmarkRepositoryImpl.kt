package com.android.kakao_book.data.repo

import com.android.kakao_book.room.bookmark.BookmarkEntity
import com.android.kakao_book.data.source.remote.local.BookmarkLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkLocalDataSource: BookmarkLocalDataSource) :
    BookmarkRepository {
    override val getAll: Flow<List<BookmarkEntity>>
        get() = bookmarkLocalDataSource.getAll

    override suspend fun delete(entity: BookmarkEntity) {
        bookmarkLocalDataSource.delete(entity)
    }

    override suspend fun insert(entity: BookmarkEntity) {
        bookmarkLocalDataSource.insert(entity)
    }
}