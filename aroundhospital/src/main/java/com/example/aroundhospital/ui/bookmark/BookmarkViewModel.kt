package com.example.aroundhospital.ui.bookmark

import androidx.lifecycle.viewModelScope
import com.example.aroundhospital.base.BaseViewModel
import com.example.aroundhospital.data.repo.BookmarkRepository
import com.example.aroundhospital.ui.adapter.BookmarkClickEventType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(bookmarkRepository: BookmarkRepository) :
    BaseViewModel() {


    val isVisibleListStateFlow = MutableStateFlow(true)

    init {
        bookmarkRepository.getAll.map {
            isVisibleListStateFlow.value = it.isNotEmpty()
            onChangedViewState(BookmarkViewState.GetBookmarkList(it.sortedByDescending { item -> item.timeStamp }))
        }.launchIn(viewModelScope)
    }

    fun itemClickType(type: BookmarkClickEventType) {
        when (type) {
            is BookmarkClickEventType.ClickItem -> {
                onChangedViewEvent(BookmarkViewEvent.ClickItem(type.item))
            }

            is BookmarkClickEventType.DeleteBookmark -> {
                onChangedViewEvent(BookmarkViewEvent.DeleteBookmark(type.item))
            }

            is BookmarkClickEventType.ShowLinkDialog -> {
                onChangedViewEvent(BookmarkViewEvent.ShowLinkDialog(type.item))
            }

            is BookmarkClickEventType.ShowPhoneDialog -> {
                onChangedViewEvent(BookmarkViewEvent.ShowPhoneDialog(type.item))
            }
        }
    }
}