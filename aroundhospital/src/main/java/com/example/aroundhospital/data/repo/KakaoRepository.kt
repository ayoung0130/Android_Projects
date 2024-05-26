package com.example.aroundhospital.data.repo

import com.example.aroundhospital.network.response.KakaoMapResponse
import retrofit2.Response

interface KakaoRepository {
    suspend fun aroundHospital(
        category: String,
        x: String,
        y: String,
        radius: Int
    ): Response<KakaoMapResponse>
}