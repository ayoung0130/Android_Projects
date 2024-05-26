package com.example.aroundhospital.ui.home

import androidx.fragment.app.viewModels
import com.example.aroundhospital.R
import com.example.aroundhospital.base.BaseFragment
import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.base.ViewState
import com.example.aroundhospital.databinding.FragmentHomeBinding
import com.example.aroundhospital.ext.showToast
import com.example.aroundhospital.ui.adapter.ViewPagerAdapter
import com.example.aroundhospital.ui.bookmark.BookmarkFragment
import com.example.aroundhospital.ui.map.MapFragment
import com.example.aroundhospital.ui.map.MapViewEvent
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val tabLayoutMediator =
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.icon = resources.obtainTypedArray(R.array.tab_icon).getDrawable(position)
        }

    override val viewModel: HomeViewModel by viewModels()
    override fun initUi() {
        val fragments = listOf(MapFragment(), BookmarkFragment())

        with(binding.viewpager) {
            adapter =
                ViewPagerAdapter(fragments, childFragmentManager, viewLifecycleOwner.lifecycle)
            isUserInputEnabled = false
            offscreenPageLimit = fragments.size
        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager, tabLayoutMediator).attach()
    }

    override fun onChangedViewState(state: ViewState) {
    }

    override fun onChangedViewEvent(event: ViewEvent) {
        when (event) {
            is HomeViewEvent.DeleteBookmark -> {
                showToast(message = "즐겨찾기가 삭제되었습니다.")
            }

            is HomeViewEvent.AddBookmark -> {
                showToast(message = "즐겨찾기가 추가되었습니다.")
            }

            is HomeViewEvent.ClickBookmarkItem -> {
                showToast(message = "병원 위치로 이동합니다.")
                binding.viewpager.currentItem = 0 // MapFragment로 이동
            }
        }
    }

}