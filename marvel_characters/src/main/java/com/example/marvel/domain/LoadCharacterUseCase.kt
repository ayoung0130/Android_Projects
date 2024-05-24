package com.example.marvel.domain

import com.example.marvel.data.repo.MarvelRepository
import com.example.marvel.ext.mapTo
import com.example.marvel.network.response.CharacterResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadCharacterUseCase @Inject constructor(private val marvelRepository: MarvelRepository) {
    operator fun invoke(
        offset: Int,
        limit: Int,
    ): Flow<LoadUiState> = flow {
        val response = marvelRepository.getCharacters(offset, limit).mapTo()
        if(response.data.isLast()) {
            emit(LoadUiState.End)
        } else {
            emit(LoadUiState.GetData(response.data.results))
        }
    }.catch {
        emit(LoadUiState.Error(it))
    }
}

sealed interface LoadUiState {
    data class GetData(val list: List<CharacterResult>) : LoadUiState
    object End : LoadUiState
    data class Error(val throwable: Throwable) : LoadUiState
}