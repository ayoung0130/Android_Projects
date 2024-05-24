package com.example.base

interface ViewState {
    object Idle : ViewState
}

interface ViewEvent


sealed interface BaseViewEvent : ViewEvent {
    data class ShowToast(val message: String) : BaseViewEvent
}