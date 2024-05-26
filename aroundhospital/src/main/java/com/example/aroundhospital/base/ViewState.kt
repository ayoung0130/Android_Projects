package com.example.aroundhospital.base

interface ViewState {
    object Idle : ViewState
}

interface ViewEvent {
    data class ShowToast(val message: String) : ViewEvent
}
