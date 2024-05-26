package com.example.aroundhospital.network.response

import com.example.aroundhospital.room.BookmarkEntity
import com.kakao.vectormap.label.LabelOptions

data class KakaoMapResponse(
    val meta: Meta,
    val documents: List<Document>
)

data class Meta(
    val total_count: Int,
    val pageble_count: Int,
    val is_end: Boolean,
    val same_name: SameName
)

data class SameName(
    val region: List<String>,
    val keyword: String,
    val selected_region: String
)

data class Document(
    val id: String,
    val place_name: String,
    val category_name: String,
    val category_group_code: String,
    val category_group_name: String,
    val phone: String,
    val address_name: String,
    val road_address_name: String,
    val x: String,
    val y: String,
    val place_url: String,
    val distance: String,
    var isBookmarked: Boolean = false
)

fun Document.toKakaoMapLabelOption(): LabelOptions =
    LabelOptions.from(place_name, com.kakao.vectormap.LatLng.from(y.toDouble(), x.toDouble())).setTag(this)

fun Document.toBookmarkEntity(): BookmarkEntity =
    BookmarkEntity(
        id = id,
        place_name = place_name,
        category_name = category_name,
        phone = phone,
        address_name = address_name,
        x = x,
        y = y,
        place_url = place_url,
    )