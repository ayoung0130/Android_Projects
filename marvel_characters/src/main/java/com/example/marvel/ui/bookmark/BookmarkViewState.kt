package com.example.marvel.ui.bookmark

import com.example.base.ViewState
import com.example.marvel.room.BookmarkEntity

sealed interface BookmarkViewState : ViewState {
    data class GetBookmarkList(val items: List<BookmarkEntity>) : BookmarkViewState
}