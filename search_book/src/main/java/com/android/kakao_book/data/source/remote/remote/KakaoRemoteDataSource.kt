package com.android.kakao_book.data.source.remote.remote

import com.android.kakao_book.network.response.KakaoSearchResponse
import retrofit2.Response

interface KakaoRemoteDataSource {
    suspend fun searchBook(
        query: String,
        size: Int,
        sort: String,
        page: Int
    ): Response<KakaoSearchResponse>
}