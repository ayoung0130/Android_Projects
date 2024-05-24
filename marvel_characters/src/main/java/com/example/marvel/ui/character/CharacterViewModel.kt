package com.example.marvel.ui.character

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewEvent
import com.example.marvel.base.BaseViewModel
import com.example.marvel.data.repo.BookmarkRepository
import com.example.marvel.domain.LoadCharacterUseCase
import com.example.marvel.domain.LoadUiState
import com.example.marvel.network.response.CharacterResult
import com.example.marvel.network.response.toBookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val loadCharacterUseCase: LoadCharacterUseCase,
    private val bookmarkRepository: BookmarkRepository,
) : BaseViewModel() {

    private var offsetCount = AtomicInteger(DEFAULT_OFFSET)

    private var isStartSearch = AtomicBoolean(false)

    private var isEnd = AtomicBoolean(false)

    val refreshState = ObservableBoolean(false)

    val isScrollBottomPosition: Function1<Boolean, Unit> = { isBottom ->
        if (!isEnd.get()) {
            if (isBottom && !isStartSearch.get()) {
                loadCharacters(offset = offsetCount.get())
            }
        }
    }

    init {
        loadCharacters(offset = DEFAULT_OFFSET, limit = DEFAULT_LIMIT)
    }

    private fun loadCharacters(
        offset: Int = DEFAULT_OFFSET, limit: Int = DEFAULT_LIMIT, isRefresh: Boolean = false
    ) {
        isStartSearch.set(true)

        if (isRefresh) {
            onChangedViewEvent(CharacterViewEvent.ClearData)
        } else {
            onChangedViewEvent(CharacterViewEvent.ShowProgress)
        }

        loadCharacterUseCase(offset = offset, limit = limit).onEach { result ->
            when (result) {
                LoadUiState.End -> {
                    isEnd.set(true)
                    onChangedViewEvent(BaseViewEvent.ShowToast("마지막 데이터입니다"))
                    onChangedViewEvent(CharacterViewEvent.HideProgress)
                }

                is LoadUiState.Error -> {
                    onChangedViewEvent(BaseViewEvent.ShowToast(result.throwable.message ?: "Error"))
                    onChangedViewEvent(CharacterViewEvent.HideProgress)
                }

                is LoadUiState.GetData -> {
                    offsetCount.addAndGet(limit)
                    val bookmarkList = bookmarkRepository.getAll.first().map { it.id }
                    val convertBookmarkList = result.list.map {
                        it.copy(isBookmarked = bookmarkList.contains(it.id))
                    }
                    onChangedViewState(CharacterViewState.GetCharacterList(convertBookmarkList))
                    onChangedViewEvent(CharacterViewEvent.HideProgress)

                    if (isRefresh) {
                        refreshState.set(false)
                        onChangedViewEvent(BaseViewEvent.ShowToast("새로고침 되었습니다."))
                    }
                    isStartSearch.set(false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onRefresh() {
        offsetCount.set(DEFAULT_OFFSET)
        refreshState.set(true)
        loadCharacters(isRefresh = true)
    }

    companion object {
        private const val DEFAULT_OFFSET = 0
        private const val DEFAULT_LIMIT = 20
    }
}