package com.android.kakao_book.ui.bookmark

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.android.kakao_book.base.BaseFragment
import com.android.kakao_book.databinding.FragmentBookmarkBinding
import com.android.kakao_book.ui.MainViewModel
import com.android.kakao_book.ui.adapter.BookmarkAdapter
import com.example.base.BaseViewEvent
import com.example.base.ViewEvent
import com.example.base.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate) {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override val viewModel by viewModels<BookmarkViewModel>()

    private val bookmarkAdapter = BookmarkAdapter {
        viewModel.deleteBookmark(it)
        mainViewModel.deleteBookmark(it)
    }

    /**
     * BookmarkFragment 초기화면 구성.
     */
    override fun initUi() {
        binding.rvBookmark.adapter = bookmarkAdapter
    }

    /**
     * BookmarkFragment의 View의 상태 변화에 따른 처리.
     */
    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {
            is BookmarkViewState.GetBookmarkList -> {
                bookmarkAdapter.addAll(viewState.items)
            }
        }
    }

    /**
     * BookmarkFragment의 View의 이벤트 변화에 따른 처리
     */
    override fun onChangedViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is BaseViewEvent.ShowToast -> {
                Toast.makeText(requireContext(), viewEvent.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}