package com.example.aroundhospital.domain.usecase

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.aroundhospital.data.repo.KakaoRepository
import com.example.aroundhospital.ext.asResult
import com.example.aroundhospital.ext.mapTo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHospitalsUseCase @Inject constructor(
    private val kakaoRepository: KakaoRepository,
    private val locationManager: LocationManager,
    @ApplicationContext private val context: Context
) {

    operator fun invoke() = flow {
        val x = getLocation()?.longitude.toString()
        val y = getLocation()?.latitude.toString()

        emit(kakaoRepository.aroundHospital(CATEGORY_HOSPITAL, x, y, RADIUS).mapTo().documents)
    }.asResult()

    operator fun invoke(latitude: Double, longitude: Double) = flow {
        val x = longitude.toString()
        val y = latitude.toString()

        emit(kakaoRepository.aroundHospital(CATEGORY_HOSPITAL, x, y, RADIUS).mapTo().documents)
    }.asResult()

    private fun getLocation(): Location? {
        var location: Location? = null
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }
        return location
    }

    companion object {
        private const val CATEGORY_HOSPITAL = "HP8"
        private const val RADIUS = 1000
    }
}