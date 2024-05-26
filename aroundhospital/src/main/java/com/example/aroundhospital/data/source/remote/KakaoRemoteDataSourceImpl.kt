package com.example.aroundhospital.data.source.remote

import com.example.aroundhospital.network.KakaoService
import com.example.aroundhospital.network.response.KakaoMapResponse
import retrofit2.Response
import javax.inject.Inject

class KakaoRemoteDataSourceImpl @Inject constructor(private val kakaoService: KakaoService) :
    KakaoRemoteDataSource {
    override suspend fun aroundHospital(
        category: String,
        x: String,
        y: String,
        radius: Int
    ): Response<KakaoMapResponse> =
        kakaoService.aroundHospital(category, x, y, radius)
}