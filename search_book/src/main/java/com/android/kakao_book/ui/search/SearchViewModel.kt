package com.android.kakao_book.ui.search

import androidx.lifecycle.viewModelScope
import com.android.kakao_book.base.BaseViewModel
import com.android.kakao_book.data.repo.BookmarkRepository
import com.android.kakao_book.domain.usecase.SearchBookUseCase
import com.android.kakao_book.domain.usecase.SearchUiState
import com.android.kakao_book.network.response.BookResult
import com.android.kakao_book.network.response.toBookmarkEntity
import com.example.base.BaseViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBookUseCase: SearchBookUseCase,
    private val bookmarkRepository: BookmarkRepository,
) : BaseViewModel() {

    private var isStartSearch = AtomicBoolean(false)
    private var isEnd = AtomicBoolean(false)
    private var pageCount = AtomicInteger(DEFAULT_PAGE)

    val inputSearch = MutableStateFlow(EMPTY_STRING)


    val isScrollBottomPosition : Function1<Boolean, Unit> = { isBottom ->
        if(isBottom && !isStartSearch.get()){
            searchBook(inputSearch.value, page = pageCount.get())
        }
    }


    init {
        inputSearch.debounce(DELAY_SEARCH_TIME)
            .filterNot { it.isEmpty() }
            .map {
                initState()
                searchBook(keyword = it)
            }
            .launchIn(viewModelScope)
    }


    private fun searchBook(
        keyword: String,
        page: Int = DEFAULT_PAGE,
        size: Int = DEFAULT_SIZE
    ) {

        searchBookUseCase(
            query = keyword,
            size = size,
            sort = SORT_ACCURACY,
            page = page
        ).onEach { result ->

            when(result){
                SearchUiState.End -> {
                    isEnd.set(true)
                    onChangedViewEvent(
                        BaseViewEvent.ShowToast(
                            "마지막 데이터 입니다."
                        )
                    )
                }
                is SearchUiState.Error -> {
                    onChangedViewEvent(
                        BaseViewEvent.ShowToast(
                            result.throwable.message ?: "Error"
                        )
                    )
                }
                is SearchUiState.GetData -> {
                    pageCount.incrementAndGet()
                    val bookmarkList = bookmarkRepository.getAll.first().map { it.isbn }
                    val convertBookmarkList = result.list.map {
                        it.copy(isBookmarked = bookmarkList.contains(it.isbn))
                    }
                    onChangedViewState(SearchViewState.GetSearchList(convertBookmarkList))
                }
            }
        }.launchIn(viewModelScope)

    }

    fun toggleBookmark(item: BookResult) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (item.isBookmarked) {
                    bookmarkRepository.delete(item.toBookmarkEntity())
                } else {
                    bookmarkRepository.insert(item.toBookmarkEntity())
                }
                onChangedViewState(SearchViewState.ToggleBookmark(item))
            } catch (e: Exception) {
                e.message
            }
        }
    }

    private fun initState(){
        isEnd.set(false)
        onChangedViewState(SearchViewState.Clear)
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val SORT_ACCURACY = "accuracy"
        private const val DELAY_SEARCH_TIME = 500L
        private const val DEFAULT_SIZE = 20
        private const val DEFAULT_PAGE = 1
    }
}




