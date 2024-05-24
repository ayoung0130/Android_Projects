package com.android.kakao_book.data.source.remote.local

import com.android.kakao_book.room.bookmark.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkLocalDataSource {

    val getAll : Flow<List<BookmarkEntity>>

    suspend fun delete(entity: BookmarkEntity)

    suspend fun insert(entity: BookmarkEntity)
}