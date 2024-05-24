package com.android.kakao_book.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.example.base.ViewEvent
import com.example.base.ViewState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

abstract class BaseActivity<V : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {

    protected lateinit var binding: V

    abstract val viewModel: BaseViewModel

    abstract fun onChangedViewState(viewState: ViewState)
    abstract fun onChangedViewEvent(viewEvent: ViewEvent)

    protected abstract fun initUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId) as V
        setContentView(binding.root)
        initUi()

        viewModel.viewStateFlow.map(::onChangedViewState).launchIn(lifecycleScope)
        viewModel.viewEventFlow.map(::onChangedViewEvent).launchIn(lifecycleScope)
    }
}