package com.example.marvel.ui

import com.example.base.ViewEvent
import com.example.base.ViewState
import com.example.marvel.network.response.CharacterResult
import com.example.marvel.room.BookmarkEntity

sealed interface MainViewState : ViewState


sealed interface MainViewEvent : ViewEvent {

    data class AddBookmark(val item: BookmarkEntity) : MainViewEvent

    data class DeleteBookmark(val item: BookmarkEntity) : MainViewEvent

}