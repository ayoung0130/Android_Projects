package com.android.kakao_book.ui.bookmark

import com.android.kakao_book.room.bookmark.BookmarkEntity
import com.example.base.ViewState


sealed interface BookmarkViewState : ViewState {
    data class GetBookmarkList(val items: List<BookmarkEntity>) : BookmarkViewState
}