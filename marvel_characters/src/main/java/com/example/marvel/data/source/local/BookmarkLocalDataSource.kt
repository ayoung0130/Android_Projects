package com.example.marvel.data.source.local

import com.example.marvel.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkLocalDataSource {
    val getAll: Flow<List<BookmarkEntity>>
    suspend fun delete(entity: BookmarkEntity)
    suspend fun insert(entity: BookmarkEntity)
}