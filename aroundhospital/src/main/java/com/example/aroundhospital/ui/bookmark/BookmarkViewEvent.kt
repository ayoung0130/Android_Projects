package com.example.aroundhospital.ui.bookmark

import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.room.BookmarkEntity

sealed interface BookmarkViewEvent : ViewEvent {
    data class ShowPhoneDialog(val item : BookmarkEntity) : BookmarkViewEvent
    data class ShowLinkDialog(val item : BookmarkEntity) : BookmarkViewEvent
    data class ClickItem(val item : BookmarkEntity) : BookmarkViewEvent
    data class DeleteBookmark(val item: BookmarkEntity) : BookmarkViewEvent
}