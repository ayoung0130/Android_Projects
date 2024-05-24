package com.example.marvel.network.response

import com.example.marvel.room.BookmarkEntity
import com.google.gson.annotations.SerializedName

data class MarvelResponse(
    @SerializedName("data")
    val data: CharacterData
)

data class CharacterData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterResult>
) {
    fun isLast() : Boolean = offset >= total
}

data class CharacterResult(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<MarvelUrl>,
    val thumbnail: MarvelThumbnail,
    val comics: MarvelComics,
    val stories: MarvelStory,
    val events: MarvelEvent,
    val series: MarvelSeries,
    val isBookmarked: Boolean = false
) {
    fun toStringUrls(): String = "urls: ${urls.size}"
    fun toStringComics(): String = "comics: ${comics.items.size}"
    fun toStringStories(): String = "stories: ${stories.items.size}"
    fun toStringEvents(): String = "events: ${events.items.size}"
    fun toStringSeries(): String = "series: ${series.items.size}"
}

data class MarvelUrl(
    val type: String,
    val url: String
)

data class MarvelComics(
    val available: Int,
    val collectionURI: String,
    val items: List<MarvelComicItem>,
    val returned: Int
)

data class MarvelComicItem(
    val resourceURI: String,
    val name: String
)

data class MarvelStory(
    val available: Int,
    val collectionURI: String,
    val items: List<MarvelStoryItem>,
    val returned: Int
)

data class MarvelStoryItem(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class MarvelEvent(
    val available: Int,
    val collectionURI: String,
    val items: List<MarvelEventItem>,
    val returned: Int
)

data class MarvelEventItem(
    val resourceURI: String,
    val name: String
)

data class MarvelSeries(
    val available: Int,
    val collectionURI: String,
    val items: List<MarvelSeriesItem>,
    val returned: Int
)

data class MarvelSeriesItem(
    val resourceURI: String,
    val name: String
)

data class MarvelThumbnail(
    val path: String,
    val extension: String
) {
    fun getThumbnail(): String = "$path.$extension"
}

fun CharacterResult.toBookmarkEntity(): BookmarkEntity =
    BookmarkEntity(
        id = id,
        name = name,
        thumbnail = thumbnail,
        urls = toStringUrls(),
        comics = toStringComics(),
        stories = toStringStories(),
        events = toStringEvents(),
        series = toStringSeries()
    )