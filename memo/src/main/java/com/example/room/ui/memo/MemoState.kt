package com.example.room.ui.memo

import com.example.room.room.SampleEntity

data class MemoState(
    val inputTitle: String = "",
    val inputContent: String = "",
    val items: List<SampleEntity> = emptyList()
)