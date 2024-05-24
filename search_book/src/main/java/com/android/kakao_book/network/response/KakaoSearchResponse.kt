package com.android.kakao_book.network.response

import com.android.kakao_book.room.bookmark.BookmarkEntity

data class KakaoSearchResponse(
    val meta: SearchMeta,
    val documents: List<BookResult>
)

data class SearchMeta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean
)

data class BookResult(
    val title: String,
    val contents: String,
    val url: String,
    val isbn: String,
    val datetime: String,
    val authors: List<String>,
    val publisher: String,
    val translators: List<String>,
    val price: Int,
    val sale_price: Int,
    val thumbnail: String,
    val status: String,
    val isBookmarked: Boolean = false
) {
    fun toStringAuthors(): String = authors.joinToString(", ")
}

fun BookResult.toBookmarkEntity(): BookmarkEntity =
    BookmarkEntity(
        title = title,
        authors = toStringAuthors(),
        contents = contents,
        isbn = isbn,
        price = price,
        thumbnail = thumbnail
    )

