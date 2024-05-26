package com.example.aroundhospital.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _viewStateFlow = MutableStateFlow<ViewState>(ViewState.Idle)
    val viewStateFlow: StateFlow<ViewState> = _viewStateFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ViewState.Idle
    )

    protected val _viewEventFlow = MutableSharedFlow<ViewEvent>()
    val viewEventFlow: Flow<ViewEvent> = _viewEventFlow

    protected fun onChangedViewState(viewState: ViewState) = viewModelScope.launch {
        _viewStateFlow.value = viewState
    }

    protected fun onChangedViewEvent(viewEvent: ViewEvent) = viewModelScope.launch {
        _viewEventFlow.emit(viewEvent)
    }
}