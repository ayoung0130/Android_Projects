package com.example.aroundhospital.ui.map

import androidx.lifecycle.viewModelScope
import com.example.aroundhospital.base.BaseViewModel
import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapEvent
import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapLabelEvent
import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapLifeCycleCallbackEvent
import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapMoveEvent
import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapReadyCallbackEvent
import com.example.aroundhospital.domain.usecase.GetCurrentLocationUseCase
import com.example.aroundhospital.domain.usecase.GetHospitalsUseCase
import com.example.aroundhospital.ext.Result
import com.example.aroundhospital.network.response.Document
import com.example.aroundhospital.ui.home.HomeViewEvent
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraUpdateFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getHospitalsUseCase: GetHospitalsUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
) : BaseViewModel() {

    private val mapCenterChannel =
        Channel<LatLng>(capacity = UNLIMITED, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    init {
        mapCenterChannel.receiveAsFlow()
            .debounce(2000)
            .map(::search)
            .launchIn(viewModelScope)
    }


    fun kakaoMapEvent(event: KakaoMapEvent) {
        when (event) {
            is KakaoMapLifeCycleCallbackEvent -> processKakaMapLifecycleEvent(event)
            is KakaoMapReadyCallbackEvent.Ready -> {
                moveCurrentLocation()
            }

            is KakaoMapLabelEvent.Click -> {
                (event.label.tag as? Document)?.let {
                    onChangedViewEvent(MapViewEvent.ShowHospitalInfo(it))
                }
            }

            is KakaoMapMoveEvent.MoveStart -> {
                onChangedViewEvent(MapViewEvent.HideHospitalInfo)
            }

            is KakaoMapMoveEvent.MoveEnd -> {
                flowOf(event.cameraPosition.position).filterNotNull().map {
                    mapCenterChannel.trySend(it)
                }.launchIn(viewModelScope)
            }
        }
    }

    fun showPhoneDialog(){
        onChangedViewEvent(MapViewEvent.ShowPhoneDialog)
    }

    fun showLinkDialog(){
        onChangedViewEvent(MapViewEvent.ShowLinkDialog)
    }

    fun toggleBookmark(){
        onChangedViewEvent(MapViewEvent.ToggleBookmark)
    }


    private fun moveCurrentLocation() {
        getCurrentLocationUseCase().onEach { result ->
            when (result) {
                is Result.Error -> {

                }
                is Result.Success -> {
                    val cameraUpdate = CameraUpdateFactory.newCenterPosition(result.data)
                    onChangedViewEvent(MapViewEvent.MoveCamera(cameraUpdate))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun search(latLng: LatLng) {
        getHospitalsUseCase(
            longitude = latLng.longitude,
            latitude = latLng.latitude
        ).onEach { result->
            when(result){
                is Result.Error -> {}
                is Result.Success -> {
                    onChangedViewEvent(MapViewEvent.GetHospitals(result.data))
                    onChangedViewEvent(ViewEvent.ShowToast("병원 정보를 불러왔습니다"))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun processKakaMapLifecycleEvent(event: KakaoMapLifeCycleCallbackEvent) {
        when (event) {
            KakaoMapLifeCycleCallbackEvent.Destroy -> {}
            is KakaoMapLifeCycleCallbackEvent.Error -> {}
            KakaoMapLifeCycleCallbackEvent.Paused -> {}
            KakaoMapLifeCycleCallbackEvent.Resumed -> {}
        }
    }
}