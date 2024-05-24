package com.example.marvel.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvel.network.response.MarvelThumbnail

@Entity(tableName = "character")
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded
    val thumbnail: MarvelThumbnail,
    @ColumnInfo(name = "urls")
    val urls: String,
    @ColumnInfo(name = "comics")
    val comics: String,
    @ColumnInfo(name = "stories")
    val stories: String,
    @ColumnInfo(name = "events")
    val events: String,
    @ColumnInfo(name = "series")
    val series: String,
)