package com.example.marvel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.base.ViewEvent
import com.example.base.ViewState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

abstract class BaseFragment<V : ViewDataBinding>(private val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> V) :
    Fragment() {
    protected lateinit var binding: V

    abstract val viewModel: BaseViewModel
    protected abstract fun initUi()
    abstract fun onChangedViewState(viewState: ViewState)
    abstract fun onChangedViewEvent(viewEvent: ViewEvent)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

        viewModel.viewStateFlow.map(::onChangedViewState).launchIn(lifecycleScope)
        viewModel.viewEventFlow.map(::onChangedViewEvent).launchIn(lifecycleScope)
    }
}