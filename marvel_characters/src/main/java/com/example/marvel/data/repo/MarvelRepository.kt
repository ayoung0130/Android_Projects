package com.example.marvel.data.repo

import com.example.marvel.network.response.MarvelResponse
import retrofit2.Response

interface MarvelRepository {
    suspend fun getCharacters(
        offset: Int,
        limit: Int
    ): Response<MarvelResponse>
}