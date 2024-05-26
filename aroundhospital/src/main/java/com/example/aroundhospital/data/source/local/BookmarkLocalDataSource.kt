package com.example.aroundhospital.data.source.local

import com.example.aroundhospital.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkLocalDataSource {
    val getAll: Flow<List<BookmarkEntity>>

    val delete: suspend (BookmarkEntity) -> Unit
    val insert: suspend (BookmarkEntity) -> Unit
}