package com.android.kakao_book.ui

import android.widget.Toast
import androidx.activity.viewModels
import com.android.kakao_book.R
import com.android.kakao_book.base.BaseActivity
import com.android.kakao_book.databinding.ActivityMainBinding
import com.android.kakao_book.ext.showToast
import com.android.kakao_book.ui.adapter.ViewPagerAdapter
import com.android.kakao_book.ui.bookmark.BookmarkFragment
import com.android.kakao_book.ui.search.SearchFragment
import com.example.base.BaseViewEvent
import com.example.base.ViewEvent
import com.example.base.ViewState
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
        val fragments = listOf(SearchFragment(), BookmarkFragment())
        binding.viewpager.adapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        binding.viewpager.offscreenPageLimit = fragments.size
        TabLayoutMediator(binding.tabLayout, binding.viewpager, tabLayoutMediator).attach()
        binding.viewModel = viewModel
    }

    override fun onChangedViewState(viewState: ViewState) {
        when (viewState) {
            is MainViewState.DeleteBookmark -> {
                showToast(message = "즐겨찾기가 삭제되었습니다.")
            }
        }
    }

    override fun onChangedViewEvent(viewEvent: ViewEvent) {
        when (viewEvent) {
            is BaseViewEvent.ShowToast -> {
                Toast.makeText(this, viewEvent.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}