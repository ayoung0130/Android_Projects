package com.example.marvel.data.repo

import com.example.marvel.data.source.local.BookmarkLocalDataSource
import com.example.marvel.room.BookmarkEntity
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