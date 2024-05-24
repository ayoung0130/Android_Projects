package com.android.kakao_book.ui

import com.android.kakao_book.room.bookmark.BookmarkEntity
import com.example.base.ViewState

sealed interface MainViewState : ViewState {
    data class DeleteBookmark(
        val item: BookmarkEntity
    ) : MainViewState

}
