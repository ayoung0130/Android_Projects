package com.example.marvel.ui

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewEvent
import com.example.marvel.base.BaseViewModel
import com.example.marvel.data.repo.BookmarkRepository
import com.example.marvel.network.response.CharacterResult
import com.example.marvel.network.response.toBookmarkEntity
import com.example.marvel.room.BookmarkEntity
import com.example.marvel.ui.character.CharacterViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
) : BaseViewModel() {

    fun toggleBookmark(item: CharacterResult) {
        viewModelScope.launch(Dispatchers.IO) {
            if (item.isBookmarked) {
                deleteBookmark(item.toBookmarkEntity())
            } else {
                addBookmark(item.toBookmarkEntity())
            }
        }
    }

    fun addBookmark(item: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.insert(item)
        onChangedViewEvent(MainViewEvent.AddBookmark(item))
        onChangedViewEvent(BaseViewEvent.ShowToast("즐겨찾기가 추가되었습니다."))
    }

    fun deleteBookmark(item: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.delete(item)
        onChangedViewEvent(MainViewEvent.DeleteBookmark(item))
        onChangedViewEvent(BaseViewEvent.ShowToast("즐겨찾기가 삭제되었습니다."))
    }
}