package com.example.marvel.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.example.marvel.base.BaseViewModel
import com.example.marvel.data.repo.BookmarkRepository
import com.example.marvel.room.BookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(bookmarkRepository: BookmarkRepository) :
    BaseViewModel() {
    init {
        bookmarkRepository.getAll.map {
            onChangedViewState(BookmarkViewState.GetBookmarkList(it))
        }.launchIn(viewModelScope)
    }
}