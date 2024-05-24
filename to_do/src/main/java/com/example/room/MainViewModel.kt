package com.example.room

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.room.data.repo.SampleRepository
import com.example.room.room.SampleEntity
import javax.inject.Inject

class MainViewModel @Inject constructor(private val sampleRepository: SampleRepository) : ViewModel() {
    var inputTitle by mutableStateOf(EMPTY_TEXT)
    var inputContent by mutableStateOf(EMPTY_TEXT)
    var items by mutableStateOf<List<SampleEntity>>(emptyList())

    init {
        getAllSample()
    }

    fun save() {
        if (inputTitle.isBlank() || inputContent.isBlank()) {
            MainViewState.ShowToast("내용을 입력해주세요")
            return
        }

        val memoEntity = SampleEntity(
            title = inputTitle,
            content = inputContent,
            date = System.currentTimeMillis().toString()
        )
        sampleRepository.insert(memoEntity)
        inputTitle = EMPTY_TEXT
        inputContent = EMPTY_TEXT
        getAllSample()
    }

    fun delete(item: SampleEntity) {
        sampleRepository.delete(item)
        getAllSample()
    }

    private fun getAllSample() {
        items = sampleRepository.getAll()
    }

    companion object {
        private const val EMPTY_TEXT = ""
    }
}



