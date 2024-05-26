package com.example.aroundhospital.ui.home

import android.location.Location
import androidx.lifecycle.viewModelScope
import com.example.aroundhospital.base.BaseViewModel
import com.example.aroundhospital.base.ViewEvent
import com.example.aroundhospital.data.repo.BookmarkRepository
import com.example.aroundhospital.domain.manager.kakaomap.KakaoMapLabelEvent
import com.example.aroundhospital.network.response.Document
import com.example.aroundhospital.network.response.toBookmarkEntity
import com.example.aroundhospital.room.BookmarkEntity
import com.example.aroundhospital.room.toDocument
import com.example.aroundhospital.ui.map.MapViewEvent
import com.kakao.vectormap.camera.CameraUpdate
import com.kakao.vectormap.camera.CameraUpdateFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : BaseViewModel() {

    fun toggleBookmark(item: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            if (item.isBookmarked) {
                deleteBookmark(item.toBookmarkEntity())
            } else {
                addBookmark(item.toBookmarkEntity())
            }
        }
    }

    fun addBookmark(item: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.insert(item)
        onChangedViewEvent(HomeViewEvent.AddBookmark(item))
    }

    fun deleteBookmark(item: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        bookmarkRepository.delete(item)
        onChangedViewEvent(HomeViewEvent.DeleteBookmark(item))
    }

    fun clickBookmarkItem(item: BookmarkEntity) = viewModelScope.launch(Dispatchers.IO) {
        onChangedViewEvent(HomeViewEvent.ClickBookmarkItem)
        onChangedViewEvent(
            MapViewEvent.MoveCamera(
                CameraUpdateFactory.newCenterPosition(
                    com.kakao.vectormap.LatLng.from(
                        (item.y).toDouble(),
                        (item.x).toDouble()
                    )
                )
            )
        )
        onChangedViewEvent(MapViewEvent.ShowHospitalInfo(item.toDocument()))
    }

}