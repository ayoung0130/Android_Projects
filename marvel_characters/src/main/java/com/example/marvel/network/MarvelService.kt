package com.example.marvel.network

import com.example.marvel.network.response.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TS,
        @Query("hash") hash: String = HASH,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<MarvelResponse>

    companion object {
        private const val API_KEY = "fe092712007a5c04b4db4b6ba7ae8483"
        private const val TS = "1681802982683"
        private const val HASH = "66118afaf66a7803274fd0fba72f884f"
    }
}