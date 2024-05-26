package com.example.aroundhospital.domain.manager.kakaomap

import com.example.aroundhospital.R
import com.example.aroundhospital.base.BaseCoroutineScope
import com.example.aroundhospital.network.response.Document
import com.example.aroundhospital.network.response.toKakaoMapLabelOption
import com.kakao.vectormap.GestureType
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdate
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTransition
import com.kakao.vectormap.label.Transition
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class KakaoMapManagerImpl : KakaoMapManager, BaseCoroutineScope() {

    private var mapView: MapView? = null
    private var kakaoMap: KakaoMap? = null
    private var labelLayer: LabelLayer? = null

    private val _kakaoMapEventChannel = Channel<KakaoMapEvent>()
    override val kakaoMapEventFlow: Flow<KakaoMapEvent> = _kakaoMapEventChannel.receiveAsFlow()

    private val kakaoMapReadyCallbackImpl = object : KakaoMapReadyCallback() {
        override fun onMapReady(kakaoMap: KakaoMap) {
            onChangedViewEvent(KakaoMapReadyCallbackEvent.Ready(kakaoMap))
            this@KakaoMapManagerImpl.kakaoMap = kakaoMap
            this@KakaoMapManagerImpl.kakaoMap?.setOnLabelClickListener(kakaoMapLableClickListener)
            this@KakaoMapManagerImpl.kakaoMap?.labelManager?.let {
                labelLayer = it.layer
            }

            this@KakaoMapManagerImpl.kakaoMap?.setOnCameraMoveStartListener(kakaoOnCameraMoveStartListener)
            this@KakaoMapManagerImpl.kakaoMap?.setOnCameraMoveEndListener(kakaoOnCameraMoveEndListener)
        }
    }

    private val kakaoOnCameraMoveStartListener =
        KakaoMap.OnCameraMoveStartListener { kakaoMap, gestureType ->
            if (gestureType != GestureType.Unknown){
                onChangedViewEvent(KakaoMapMoveEvent.MoveStart(kakaoMap))
            }
        }

    private val kakaoOnCameraMoveEndListener =
        KakaoMap.OnCameraMoveEndListener { kakaoMap, cameraPosition, gestureType ->
            onChangedViewEvent(KakaoMapMoveEvent.MoveEnd(cameraPosition))
        }

    private val kakaoMapLableClickListener =
        KakaoMap.OnLabelClickListener { kakaoMap, layer, label ->
            onChangedViewEvent(KakaoMapLabelEvent.Click(kakaoMap, layer, label))
        }


    private val kakaoMapLifeCycleCallbackImpl = object : MapLifeCycleCallback() {
        override fun onMapResumed() {
            super.onMapResumed()
            onChangedViewEvent(KakaoMapLifeCycleCallbackEvent.Resumed)
        }

        override fun onMapPaused() {
            super.onMapPaused()
            onChangedViewEvent(KakaoMapLifeCycleCallbackEvent.Paused)
        }

        override fun onMapDestroy() {
            onChangedViewEvent(KakaoMapLifeCycleCallbackEvent.Destroy)
            _kakaoMapEventChannel.close()
        }

        override fun onMapError(error: Exception?) {
            onChangedViewEvent(KakaoMapLifeCycleCallbackEvent.Error(error))
        }
    }


    override fun init(mapView: MapView) {
        this@KakaoMapManagerImpl.mapView = mapView
        this@KakaoMapManagerImpl.mapView?.start(
            kakaoMapLifeCycleCallbackImpl,
            kakaoMapReadyCallbackImpl
        )
    }

    override fun addLabels(items: List<Document>) {
        clearLabels()   // 전의 라벨들 제거
        //todo label style 도 나눌수 있는 방법 검토.
        val styles = kakaoMap?.labelManager!!
            .addLabelStyles(
                LabelStyles.from(
                    LabelStyle.from(R.drawable.hospital_marker)
                        .setIconTransition(LabelTransition.from(Transition.None, Transition.None))
                )
            )
        items.map { it.toKakaoMapLabelOption() }
        labelLayer?.addLabels(items.map { it.toKakaoMapLabelOption().setStyles(styles) })
    }

    override fun moveCamera(cameraUpdate: CameraUpdate) {
        kakaoMap?.moveCamera(cameraUpdate)
    }

    override fun clearLabels() {
        labelLayer?.removeAll()  // 모든 라벨 제거
    }

    private fun onChangedViewEvent(event: KakaoMapEvent) = launch {
        _kakaoMapEventChannel
            .trySend(event)
            .onSuccess { }
            .onFailure { it?.let(::handleException) }
            .onClosed { it?.let(::handleException) }
    }

    override fun handleException(exception: Throwable) {
        super.handleException(exception)
        onChangedViewEvent(KakaoMapLifeCycleCallbackEvent.Error(exception as? Exception))
    }
}
