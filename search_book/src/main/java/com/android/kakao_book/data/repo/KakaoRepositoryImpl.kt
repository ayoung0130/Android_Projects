package com.android.kakao_book.data.repo

import com.android.kakao_book.data.source.remote.remote.KakaoRemoteDataSource
import com.android.kakao_book.network.response.KakaoSearchResponse
import retrofit2.Response
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(private val kakaoRemoteDataSource: KakaoRemoteDataSource) :
    KakaoRepository {

    override suspend fun searchBook(
        query: String,
        size: Int,
        sort: String,
        page: Int
    ): Response<KakaoSearchResponse> =
        kakaoRemoteDataSource.searchBook(query, size, sort, page)
}