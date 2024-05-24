package com.example.room

import com.example.room.room.SampleEntity

sealed class MainViewState {
    data class GetSampleList(val items : List<SampleEntity>) : MainViewState()
    data class ShowToast(val message : String) : MainViewState()
}
