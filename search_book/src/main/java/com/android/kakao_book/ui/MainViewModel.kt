package com.android.kakao_book.ui

import com.android.kakao_book.base.BaseViewModel
import com.android.kakao_book.room.bookmark.BookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    fun deleteBookmark(item: BookmarkEntity) {
        onChangedViewState(MainViewState.DeleteBookmark(item))
    }

}