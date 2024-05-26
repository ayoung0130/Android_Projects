package com.example.room.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.data.repo.MemoRepository
import com.example.room.room.MemoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
open class MemoViewModel @Inject constructor(private val sampleRepository: MemoRepository) :
    ViewModel() {

    private val _memoState = MutableStateFlow(MemoState())
    val memoState: StateFlow<MemoState> get() = _memoState.asStateFlow()

    init {
        sampleRepository.getAll.map {
            _memoState.value = _memoState.value.copy(items = it)
        }.launchIn(viewModelScope)
    }

    fun onChangedInputTitle(title: String) {
        _memoState.value = _memoState.value.copy(inputTitle = title)
    }

    fun onChangedInputContent(content: String) {
        _memoState.value = _memoState.value.copy(inputContent = content)
    }

    fun save() = viewModelScope.launch(Dispatchers.IO) {
        if (_memoState.value.inputTitle.isBlank() || _memoState.value.inputContent.isBlank()) {
            return@launch
        }
        val memoEntity = MemoEntity(
            title = _memoState.value.inputTitle,
            content = _memoState.value.inputContent,
            date = getCurrentData()
        )

        sampleRepository.insert(memoEntity)

        _memoState.value = MemoState(items = _memoState.value.items)
    }


    fun delete(item: MemoEntity) = viewModelScope.launch(Dispatchers.IO) {
        sampleRepository.delete(item)
    }

    private fun getCurrentData(): String {
        val dataFormat = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
        return dataFormat.format(Date())
    }
}
