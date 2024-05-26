package com.example.aroundhospital.ui.bookmark

import com.example.aroundhospital.base.ViewState
import com.example.aroundhospital.room.BookmarkEntity

sealed interface BookmarkViewState : ViewState {
    data class GetBookmarkList(val items: List<BookmarkEntity>) : BookmarkViewState
}