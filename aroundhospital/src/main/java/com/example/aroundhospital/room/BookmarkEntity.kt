package com.example.aroundhospital.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aroundhospital.network.response.Document

@Entity(tableName = "hospital")
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "place_name")
    val place_name: String,
    @ColumnInfo(name = "category_name")
    val category_name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "address_name")
    val address_name: String,
    @ColumnInfo(name = "x")
    val x: String,
    @ColumnInfo(name = "y")
    val y: String,
    @ColumnInfo(name = "place_url")
    val place_url: String,
    @ColumnInfo(name = "timestamp")
    val timeStamp: Long = System.currentTimeMillis()
)

fun BookmarkEntity.toDocument(): Document {
    return Document(
        id = this.id,
        place_name = this.place_name,
        category_name = this.category_name,
        category_group_code = "",
        category_group_name = "",
        phone = this.phone,
        address_name = this.address_name,
        road_address_name = "",
        x = this.x,
        y = this.y,
        place_url = this.place_url,
        distance = "",
        isBookmarked = true
    )
}
