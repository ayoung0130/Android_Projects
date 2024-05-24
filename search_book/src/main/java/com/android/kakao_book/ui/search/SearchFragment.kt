package com.android.kakao_book.ui.search

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.kakao_book.base.BaseFragment
import com.android.kakao_book.databinding.FragmentSearchBinding
import com.android.kakao_book.ext.showToast
import com.android.kakao_book.network.response.BookResult
import com.android.kakao_book.ui.MainViewModel
import com.android.kakao_book.ui.MainViewState
import com.android.kakao_book.ui.adapter.SearchAdapter
import com.example.base.BaseViewEvent
import com.example.base.ViewEvent
import com.example.base.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    ViewState {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override val viewModel by viewModels<SearchViewModel>()

    private val searchAdapter = SearchAdapter {
        viewModel.toggleBookmark(it)
    }

    /**
     * SearchFragment 초기화면 구성.
     */
    override fun initUi() {
        binding.viewModel = viewModel
        binding.rvSearch.adapter = searchAdapter
        mainViewModel.viewStateFlow.onEach(::onChangedViewState)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    /**
     * SearchFragment의 View의 상태 변화에 따른 처리.
     */
    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {
            is SearchViewState.GetSearchList -> {
                binding.rvSearch.isVisible = true
                binding.tvNoResult.isVisible = false
                searchAdapter.addItems(viewState.list)
            }

            is SearchViewState.ShowToast -> {
                showToast(message = viewState.message)
            }

            SearchViewState.EmptyResult -> {
                binding.rvSearch.isVisible = false
                binding.tvNoResult.isVisible = true
            }

            SearchViewState.Clear -> {
                searchAdapter.clearItems()
            }

            is SearchViewState.ToggleBookmark -> {
                searchAdapter.toggleBookmark(viewState.item)
            }

            is MainViewState.DeleteBookmark -> {
                searchAdapter.deleteBookmark(viewState.item)
            }

        }
    }

    /**
     * SearchFragment의 View의 이벤트 변화에 따른 처리
     */
    override fun onChangedViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is BaseViewEvent.ShowToast -> {
                Toast.makeText(requireContext(), viewEvent.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}