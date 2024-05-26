package com.example.aroundhospital.ui.map

import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.network.response.Document
import com.example.aroundhospital.ui.home.HomeViewEvent
import com.kakao.vectormap.camera.CameraUpdate

sealed interface MapViewEvent : ViewEvent {
    data class GetHospitals(val list: List<Document>) : MapViewEvent
    data class ShowHospitalInfo(val item: Document) : MapViewEvent
    data object HideHospitalInfo : MapViewEvent

    data class MoveCamera(val cameraUpdate: CameraUpdate) : MapViewEvent

    data object ShowPhoneDialog : MapViewEvent
    data object ShowLinkDialog : MapViewEvent

    data object ToggleBookmark : MapViewEvent
}