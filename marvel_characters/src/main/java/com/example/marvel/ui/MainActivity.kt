package com.example.marvel.ui

import android.widget.Toast
import androidx.activity.viewModels
import com.example.base.BaseViewEvent
import com.example.base.ViewEvent
import com.example.base.ViewState
import com.example.marvel.R
import com.example.marvel.base.BaseActivity
import com.example.marvel.databinding.ActivityMainBinding
import com.example.marvel.ext.showToast
import com.example.marvel.ui.adapter.ViewPagerAdapter
import com.example.marvel.ui.bookmark.BookmarkFragment
import com.example.marvel.ui.character.CharacterFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel by viewModels<MainViewModel>()

    private val tabLayoutMediator =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = resources.getStringArray(R.array.tab_title)[position]
            tab.icon = resources.obtainTypedArray(R.array.tab_icon).getDrawable(position)
        }

    override fun initUi() {
        val fragments = listOf(CharacterFragment(), BookmarkFragment())
        with(binding) {
            viewPager.adapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
            binding.viewPager.offscreenPageLimit = fragments.size
            viewModel = this@MainActivity.viewModel
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager, tabLayoutMediator).attach()
    }

    override fun onChangedViewState(viewState: ViewState) {}

    override fun onChangedViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is BaseViewEvent.ShowToast -> {
                showToast(message = viewEvent.message)
            }
        }
    }
}