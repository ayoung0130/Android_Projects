package com.example.aroundhospital.ui.home

import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.room.BookmarkEntity

sealed interface HomeViewEvent : ViewEvent {
    data object ClickBookmarkItem : HomeViewEvent
    data class AddBookmark(val item: BookmarkEntity) : HomeViewEvent
    data class DeleteBookmark(val item: BookmarkEntity) : HomeViewEvent

}