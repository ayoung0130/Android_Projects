package com.example.aroundhospital.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.aroundhospital.BR
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

abstract class BaseFragment<V : ViewDataBinding>(private val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> V) :
    Fragment() {
    protected lateinit var binding: V

    abstract val viewModel: BaseViewModel

    protected abstract fun initUi()
    abstract fun onChangedViewState(state: ViewState)
    abstract fun onChangedViewEvent(event: ViewEvent)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(inflater, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        initUi()
        viewModel.viewStateFlow.map(::onChangedViewState).launchIn(lifecycleScope)
        viewModel.viewEventFlow.map(::onChangedViewEvent).launchIn(lifecycleScope)
        return binding.root
    }
}