package com.example.aroundhospital.data.repo

import com.example.aroundhospital.data.source.remote.KakaoRemoteDataSource
import com.example.aroundhospital.network.response.KakaoMapResponse
import retrofit2.Response
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(private val kakaoRemoteDataSource: KakaoRemoteDataSource) :
    KakaoRepository {
    override suspend fun aroundHospital(
        category: String,
        x: String,
        y: String,
        radius: Int
    ): Response<KakaoMapResponse> =
        kakaoRemoteDataSource.aroundHospital(category, x, y, radius)
}