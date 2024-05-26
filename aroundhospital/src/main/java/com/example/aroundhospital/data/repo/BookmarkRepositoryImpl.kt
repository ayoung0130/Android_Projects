package com.example.aroundhospital.data.repo

import com.example.aroundhospital.data.source.local.BookmarkLocalDataSource
import com.example.aroundhospital.room.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarkLocalDataSource: BookmarkLocalDataSource) :
    BookmarkRepository {
    override val getAll: Flow<List<BookmarkEntity>>
        get() = bookmarkLocalDataSource.getAll
    override val delete: suspend (BookmarkEntity) -> Unit
        get() = bookmarkLocalDataSource.delete
    override val insert: suspend (BookmarkEntity) -> Unit
        get() = bookmarkLocalDataSource.insert

}