package com.example.aroundhospital.ui.bookmark

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.aroundhospital.base.BaseFragment
import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.base.ViewState
import com.example.aroundhospital.databinding.FragmentBookmarkBinding
import com.example.aroundhospital.ext.showToast
import com.example.aroundhospital.ui.adapter.BookmarkAdapter
import com.example.aroundhospital.ui.dialog.LinkDialogFragment
import com.example.aroundhospital.ui.dialog.PhoneDialogFragment
import com.example.aroundhospital.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate) {
    private val homeViewModel by viewModels<HomeViewModel>(
        ownerProducer = { requireParentFragment() }
    )

    override val viewModel by viewModels<BookmarkViewModel>()

    private val bookmarkAdapter by lazy { BookmarkAdapter(viewModel::itemClickType) }

    override fun initUi() {
        binding.rvBookmark.adapter = bookmarkAdapter
        homeViewModel.viewEventFlow.map(::onChangedViewEvent)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onChangedViewState(state: ViewState) {
        when (state) {
            is BookmarkViewState.GetBookmarkList -> {
                bookmarkAdapter.submitList(state.items)
            }
        }
    }

    override fun onChangedViewEvent(event: ViewEvent) {
        when (event) {
            is BookmarkViewEvent.ShowPhoneDialog -> {
                PhoneDialogFragment(event.item.phone).show(
                    parentFragmentManager, "PhoneDialogFragment"
                )
            }

            is BookmarkViewEvent.ShowLinkDialog -> {
                LinkDialogFragment(event.item.place_url).show(
                    parentFragmentManager, "LinkDialogFragment"
                )
            }

            is BookmarkViewEvent.DeleteBookmark -> {
                homeViewModel.deleteBookmark(event.item)
            }

            is BookmarkViewEvent.ClickItem -> {
                homeViewModel.clickBookmarkItem(event.item)
            }

            is ViewEvent.ShowToast -> showToast(event.message)
        }
    }
}