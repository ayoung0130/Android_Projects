package com.example.room.ui.memo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.room.room.SampleEntity

@Composable
fun MemoItem(item: SampleEntity, onDeleteClick: (SampleEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("No: ${item.num}")
            Text("Title: ${item.title}")
            Text("Content: ${item.content}")
            Text("Date: ${item.date}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { onDeleteClick(item) }) {
                Text("Delete")
            }
        }
    }
}

@Preview
@Composable
fun PreviewMemoItem() {
    val sampleEntity = SampleEntity(
        num = 1,
        title = "Sample Title",
        content = "Sample Content",
        date = "2024-05-25"
    )
    MemoItem(item = sampleEntity, onDeleteClick = {})
}