package com.example.aroundhospital.ui.map

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.aroundhospital.base.BaseFragment
import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.base.ViewState
import com.example.aroundhospital.databinding.FragmentMapBinding
import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapManager
import com.example.aroundhospital.ext.showToast
import com.example.aroundhospital.ui.dialog.LinkDialogFragment
import com.example.aroundhospital.ui.dialog.PhoneDialogFragment
import com.example.aroundhospital.ui.home.HomeViewEvent
import com.example.aroundhospital.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate) {
    override val viewModel: MapViewModel by viewModels()

    private val homeViewModel: HomeViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    @Inject
    lateinit var kakaoMapManager: KakaoMapManager

    override fun initUi() {
        kakaoMapManager.init(binding.mapView)
        kakaoMapManager.kakaoMapEventFlow.map(viewModel::kakaoMapEvent).launchIn(lifecycleScope)
        homeViewModel.viewEventFlow.map(::onChangedViewEvent).launchIn(lifecycleScope)
    }

    override fun onChangedViewState(state: ViewState) {
    }

    override fun onChangedViewEvent(event: ViewEvent) {
        when (event) {
            is MapViewEvent.GetHospitals -> {
                kakaoMapManager.addLabels(event.list)
            }

            is MapViewEvent.ShowHospitalInfo -> {
                binding.hospitalInfoView.isVisible = true
                binding.document = event.item
            }

            is MapViewEvent.HideHospitalInfo -> {
                binding.hospitalInfoView.isVisible = false
            }

            is MapViewEvent.MoveCamera -> {
                kakaoMapManager.moveCamera(event.cameraUpdate)
            }

            is HomeViewEvent.AddBookmark -> {
                if(binding.hospitalInfoView.isVisible){
                    binding.document = binding.document?.copy(isBookmarked = true)
                    binding.document?.isBookmarked = true
                }
            }

            is HomeViewEvent.DeleteBookmark -> {
                if(binding.hospitalInfoView.isVisible){
                    binding.document = binding.document?.copy(isBookmarked = false)
                    binding.document?.isBookmarked = false
                }
            }

            is MapViewEvent.ShowPhoneDialog -> {
                val phoneNumber = binding.document?.phone ?: return
                val dialog = PhoneDialogFragment(phoneNumber)
                dialog.show(parentFragmentManager, "PhoneDialogFragment")
            }

            is MapViewEvent.ShowLinkDialog -> {
                val link = binding.document?.place_url ?: return
                val dialog = LinkDialogFragment(link)
                dialog.show(parentFragmentManager, "LinkDialogFragment")
            }

            is MapViewEvent.ToggleBookmark -> {
                homeViewModel.toggleBookmark(binding.document ?: return)
            }

            is ViewEvent.ShowToast -> {
                showToast(event.message)
            }
        }
    }
}