package com.example.marvel.ui.character

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.base.BaseViewEvent
import com.example.base.ViewEvent
import com.example.base.ViewState
import com.example.marvel.base.BaseFragment
import com.example.marvel.databinding.FragmentCharacterBinding
import com.example.marvel.ext.showToast
import com.example.marvel.network.response.toBookmarkEntity
import com.example.marvel.ui.MainViewEvent
import com.example.marvel.ui.MainViewModel
import com.example.marvel.ui.MainViewState
import com.example.marvel.ui.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(FragmentCharacterBinding::inflate),
    ViewState {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override val viewModel by viewModels<CharacterViewModel>()

    private val characterAdapter = CharacterAdapter {
        mainViewModel.toggleBookmark(it)
    }

    /**
     * CharacterFragment 초기화면 구성
     */
    override fun initUi() {
        binding.viewModel = viewModel
        binding.rvCharacter.adapter = characterAdapter
        mainViewModel.viewEventFlow.map(::onChangedViewEvent)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }


    /**
     * CharacterFragment View의 상태 변화에 따른 처리
     */
    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {
            is CharacterViewState.GetCharacterList -> {
                binding.rvCharacter.isVisible = true
                binding.tvFailureResult.isVisible = false
                characterAdapter.addItems(viewState.list)
            }

            is CharacterViewState.Error -> {
                binding.rvCharacter.isVisible = true
                binding.tvFailureResult.isVisible = false
            }
        }
    }

    /**
     * CharacterFragment View의 이벤트 변화에 따른 처리
     */
    override fun onChangedViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is BaseViewEvent.ShowToast -> {
                Toast.makeText(requireContext(), viewEvent.message, Toast.LENGTH_SHORT).show()
            }

            is MainViewEvent.DeleteBookmark -> {
                characterAdapter.toggleBookmark(viewEvent.item)
            }

            is MainViewEvent.AddBookmark -> {
                characterAdapter.toggleBookmark(viewEvent.item)
            }

            is CharacterViewEvent.ShowProgress -> {
                binding.progress.isVisible = true
            }

            is CharacterViewEvent.HideProgress -> {
                binding.progress.isVisible = false
            }

            is CharacterViewEvent.ClearData -> {
                characterAdapter.clearItems()
            }
        }
    }
}