package com.android.kakao_book

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.kakao_book.network.KakaoService
import com.android.kakao_book.network.response.BookResult
import java.io.IOException

class BookPagingSource(
    private val service: KakaoService,
    private val query: String
) : PagingSource<Int, BookResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookResult> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = query

        return try {
            val response = service.searchBook(apiQuery, params.loadSize, SORT_ACCURACY ,position)
            val repos = response.body()?.documents ?: emptyList()
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, BookResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val SORT_ACCURACY = "Accuracy"
        private const val NETWORK_PAGE_SIZE = 20
    }
}