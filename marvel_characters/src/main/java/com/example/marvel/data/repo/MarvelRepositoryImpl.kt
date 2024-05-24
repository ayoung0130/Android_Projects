package com.example.marvel.data.repo

import com.example.marvel.data.source.remote.MarvelRemoteDataSource
import com.example.marvel.network.response.MarvelResponse
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val marvelRemoteDataSource: MarvelRemoteDataSource) :
    MarvelRepository {
    override suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): Response<MarvelResponse> =
        marvelRemoteDataSource.getCharacters(offset, limit)
}