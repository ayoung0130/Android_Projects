package com.example.aroundhospital.domain.manager.kakaomap

import com.example.aroundhospital.network.response.Document
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdate
import kotlinx.coroutines.flow.Flow

interface KakaoMapManager {

    val kakaoMapEventFlow: Flow<KakaoMapEvent>
    fun init(mapView: MapView)
    fun addLabels(items : List<Document>)
    fun moveCamera(cameraUpdate: CameraUpdate)
    fun clearLabels()
}
