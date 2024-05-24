package com.example.marvel.ui.character

import com.example.base.ViewEvent
import com.example.base.ViewState
import com.example.marvel.network.response.CharacterResult

interface CharacterViewState : ViewState {
    data class GetCharacterList(val list: List<CharacterResult>) : CharacterViewState
    object Error : CharacterViewState
}

sealed interface CharacterViewEvent : ViewEvent {

    object ClearData : CharacterViewEvent

    object ShowProgress : CharacterViewEvent

    object HideProgress : CharacterViewEvent
}