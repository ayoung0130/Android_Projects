package com.android.kakao_book.network

import com.android.kakao_book.network.response.KakaoSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoService {
    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET("v3/search/book")
    suspend fun searchBook(
        @Query("query") query: String,
        @Query("size") size: Int = 20,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int
    ): Response<KakaoSearchResponse>

    companion object {
        private const val REST_API_KEY = "fae587d1f38f90e171b6881f35a85e4f"
    }
}