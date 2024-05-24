package com.android.kakao_book.data.repo

import com.android.kakao_book.room.bookmark.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    val getAll : Flow<List<BookmarkEntity>>
    suspend fun delete(entity: BookmarkEntity)

    suspend fun insert(entity: BookmarkEntity)
}
