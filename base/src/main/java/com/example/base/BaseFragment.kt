package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<V : ViewBinding>(private val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> V) :
    Fragment() {

    protected lateinit var binding: V

    abstract val viewModel : BaseViewModel

    abstract fun initUi()

    abstract fun onChangedViewState(viewState: ViewState)

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
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, ::onChangedViewState)
    }
}