package com.android.kakao_book.data.repo

import com.android.kakao_book.network.response.KakaoSearchResponse
import retrofit2.Response

interface KakaoRepository {
    suspend fun searchBook(
        query: String,
        size: Int,
        sort: String,
        page: Int
    ): Response<KakaoSearchResponse>
}