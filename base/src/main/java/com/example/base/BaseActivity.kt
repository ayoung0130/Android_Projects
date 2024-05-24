package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V : ViewBinding>(
    private val bindingFactory: ((LayoutInflater) -> V)? = null,
    @LayoutRes private val layoutResId: Int? = null
) : AppCompatActivity() {

    protected lateinit var binding: V

    abstract val viewModel: BaseViewModel

    abstract fun onChangedViewState(viewState: ViewState)

    protected abstract fun initUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory?.invoke(layoutInflater)
            ?: DataBindingUtil.setContentView(this, layoutResId ?: 0) as V

        setContentView(binding.root)
        initUi()

        viewModel.viewStateLiveData.observe(this, ::onChangedViewState)
    }
}
