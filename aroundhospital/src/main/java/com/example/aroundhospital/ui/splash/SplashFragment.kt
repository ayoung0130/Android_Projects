package com.example.aroundhospital.ui.splash

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aroundhospital.base.BaseFragment
import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.base.ViewState
import com.example.aroundhospital.databinding.FragmentSplashBinding
import com.example.aroundhospital.ext.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override val viewModel: SplashViewModel by viewModels()

    private val permissionResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                onChangedViewState(SplashViewState.RouteMap)
            } else {
                onChangedViewEvent(ViewEvent.ShowToast("위치 권한을 허용해주세요."))
            }
        }

    override fun initUi() {}

    override fun onChangedViewState(state: ViewState) {
        when (state) {
            is SplashViewState.RouteMap -> {
                findNavController().navigate(SplashFragmentDirections.actionSplashToHome())
            }

            is SplashViewState.RequestPermission -> {
                permissionResultLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    override fun onChangedViewEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.ShowToast -> showToast(event.message)
        }
    }
}