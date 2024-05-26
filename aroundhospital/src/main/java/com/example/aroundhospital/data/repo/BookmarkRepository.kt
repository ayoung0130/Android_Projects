package com.example.aroundhospital.data.repo

import com.example.aroundhospital.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    val getAll: Flow<List<BookmarkEntity>>

    val delete: suspend (BookmarkEntity) -> Unit
    val insert: suspend (BookmarkEntity) -> Unit

}