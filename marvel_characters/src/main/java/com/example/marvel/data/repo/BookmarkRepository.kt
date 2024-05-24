package com.example.marvel.data.repo

import com.example.marvel.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    val getAll : Flow<List<BookmarkEntity>>
    suspend fun delete(entity: BookmarkEntity)
    suspend fun insert(entity: BookmarkEntity)
}