package com.example.room.ui.memo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.room.ui.memo.component.MemoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoScreen(
    viewModel: MemoViewModel = hiltViewModel()
) {
    val memoState by viewModel.memoState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("memo") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(memoState.items) { item ->
                    MemoItem(item = item, onDeleteClick = viewModel::delete)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = memoState.inputTitle,
                onValueChange = viewModel::onChangedInputTitle,
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = memoState.inputContent,
                onValueChange = viewModel::onChangedInputContent,
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { viewModel.save() }, modifier = Modifier.align(Alignment.End)) {
                Text("Save")
            }
        }
    }
}
