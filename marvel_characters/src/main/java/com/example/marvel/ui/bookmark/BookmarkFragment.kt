package com.example.marvel.ui.bookmark

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.base.BaseViewEvent
import com.example.base.ViewEvent
import com.example.base.ViewState
import com.example.marvel.base.BaseFragment
import com.example.marvel.databinding.FragmentBookmarkBinding
import com.example.marvel.ui.MainViewEvent
import com.example.marvel.ui.MainViewModel
import com.example.marvel.ui.adapter.BookmarkAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate) {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override val viewModel by viewModels<BookmarkViewModel>()

    private val bookmarkAdapter = BookmarkAdapter {
        mainViewModel.deleteBookmark(it)
    }

    override fun initUi() {
        binding.rvBookmark.adapter = bookmarkAdapter
        mainViewModel.viewEventFlow.map(::onChangedViewEvent)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {
            is BookmarkViewState.GetBookmarkList -> {
                bookmarkAdapter.addAll(viewState.items)
            }
        }
    }

    override fun onChangedViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is BaseViewEvent.ShowToast -> {
                Toast.makeText(requireContext(), viewEvent.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}