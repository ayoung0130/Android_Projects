package com.example.room.ui

import androidx.compose.runtime.Composable
import com.example.room.base.BaseActivity
import com.example.room.ui.memo.MemoScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Composable
    override fun Content() {
        MemoScreen()
    }
}