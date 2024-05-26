package com.example.aroundhospital.domain.manager.kakaomap

import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraPosition
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.LabelLayer

sealed interface KakaoMapEvent


sealed interface KakaoMapLifeCycleCallbackEvent : KakaoMapEvent {
    object Resumed : KakaoMapLifeCycleCallbackEvent
    object Paused : KakaoMapLifeCycleCallbackEvent
    object Destroy : KakaoMapLifeCycleCallbackEvent
    data class Error(val error: Exception?) : KakaoMapLifeCycleCallbackEvent
}

sealed interface KakaoMapReadyCallbackEvent : KakaoMapEvent {
    data class Ready(val kakaoMap: KakaoMap) : KakaoMapReadyCallbackEvent
}

sealed interface KakaoMapLabelEvent : KakaoMapEvent {
    data class Click(val kakaoMap: KakaoMap, val layer: LabelLayer, val label: Label) : KakaoMapLabelEvent
}

sealed interface KakaoMapMoveEvent : KakaoMapEvent {
    data class MoveStart(val kakaoMap: KakaoMap) : KakaoMapMoveEvent
    data class MoveEnd(val cameraPosition: CameraPosition) : KakaoMapMoveEvent
}