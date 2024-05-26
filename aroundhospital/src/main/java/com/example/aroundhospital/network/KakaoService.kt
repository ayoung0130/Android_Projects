package com.example.aroundhospital.network

import com.example.aroundhospital.network.response.KakaoMapResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoService {
    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET("v2/local/search/category")

    suspend fun aroundHospital(
        @Query("category_group_code") category: String,
        @Query("x") x: String,
        @Query("y") y: String,
        @Query("radius") radius: Int,
    ): Response<KakaoMapResponse>

    companion object {
        private const val REST_API_KEY = "fae587d1f38f90e171b6881f35a85e4f"
    }
}