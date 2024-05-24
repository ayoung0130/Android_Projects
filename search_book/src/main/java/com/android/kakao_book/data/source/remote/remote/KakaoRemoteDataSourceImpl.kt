package com.android.kakao_book.data.source.remote.remote

import com.android.kakao_book.network.KakaoService
import com.android.kakao_book.network.response.KakaoSearchResponse
import retrofit2.Response
import javax.inject.Inject

class KakaoRemoteDataSourceImpl @Inject constructor(private val kakaoService: KakaoService) : KakaoRemoteDataSource {

    override suspend fun searchBook(
        query: String,
        size: Int,
        sort: String,
        page: Int
    ): Response<KakaoSearchResponse> =
        kakaoService.searchBook(query = query, size = size, sort = sort, page = page)
}