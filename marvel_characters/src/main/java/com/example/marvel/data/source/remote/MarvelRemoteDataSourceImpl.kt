package com.example.marvel.data.source.remote

import com.example.marvel.network.MarvelService
import com.example.marvel.network.response.MarvelResponse
import retrofit2.Response
import javax.inject.Inject

class MarvelRemoteDataSourceImpl @Inject constructor(private val marvelService: MarvelService) :
    MarvelRemoteDataSource {
    override suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): Response<MarvelResponse> =
        marvelService.getCharacters(offset = offset, limit = limit)
}