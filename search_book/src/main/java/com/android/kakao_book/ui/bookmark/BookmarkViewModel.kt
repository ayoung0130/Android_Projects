package com.android.kakao_book.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.android.kakao_book.base.BaseViewModel
import com.android.kakao_book.data.repo.BookmarkRepository
import com.android.kakao_book.room.bookmark.BookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val bookmarkRepository: BookmarkRepository) :
    BaseViewModel() {

    init {
        bookmarkRepository.getAll.map {
            onChangedViewState(BookmarkViewState.GetBookmarkList(it))
        }.launchIn(viewModelScope)
    }

    fun deleteBookmark(bookmarkEntity: BookmarkEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.delete(bookmarkEntity)
        }
    }
}