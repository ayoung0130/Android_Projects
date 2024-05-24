package com.android.kakao_book.ui.search

import com.android.kakao_book.network.response.BookResult
import com.example.base.ViewState

sealed interface SearchViewState : ViewState {
    object EmptyResult : SearchViewState
    data class ShowToast(val message: String) : SearchViewState
    data class GetSearchList(val list: List<BookResult>) : SearchViewState
    data class ToggleBookmark(val item: BookResult) : SearchViewState

    object Clear : SearchViewState
}