package com.android.kakao_book.room.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "isbn")
    val isbn: String,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "authors")
    val authors: String = "",
    @ColumnInfo(name = "contents")
    val contents: String = "",
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String = "",
)