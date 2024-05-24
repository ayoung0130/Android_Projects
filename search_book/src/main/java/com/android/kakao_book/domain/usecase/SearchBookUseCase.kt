package com.android.kakao_book.domain.usecase

import com.android.kakao_book.data.repo.KakaoRepository
import com.android.kakao_book.exception.EmptyBodyException
import com.android.kakao_book.exception.NetworkFailureException
import com.android.kakao_book.exception.SearchErrorException
import com.android.kakao_book.network.response.BookResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(private val kakaoRepository: KakaoRepository) {
    operator fun invoke(
        query: String,
        size: Int,
        sort: String,
        page: Int
    ): Flow<SearchUiState> = flow {
        val response = kakaoRepository.searchBook(query, size, sort, page)
        if (response.isSuccessful) {
            response.body()?.let {
                if (it.meta.is_end) {
                    if(it.meta.total_count == 0){
                        throw SearchErrorException("[${response.code()} - ${response.raw()}]")
                    }
                    emit(SearchUiState.End)
                } else {
                    val bookResults: List<BookResult> =
                        response.body()?.documents
                            ?: throw EmptyBodyException("[${response.code()} - ${response.raw()}]")
                    emit(SearchUiState.GetData(bookResults))
                }
            } ?: throw SearchErrorException("[${response.code()} - ${response.raw()}]")
        } else {
            throw NetworkFailureException("[${response.code()} - ${response.raw()}]")
        }
    }.catch {
        emit(SearchUiState.Error(it))
    }
}


sealed interface SearchUiState {
    data class GetData(val list: List<BookResult>) : SearchUiState
    object End : SearchUiState
    data class Error(val throwable: Throwable) : SearchUiState
}