package com.example.marvel.data.source.remote

import com.example.marvel.network.response.MarvelResponse
import retrofit2.Response

interface MarvelRemoteDataSource {
    suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): Response<MarvelResponse>
}