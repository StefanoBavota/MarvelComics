package com.example.marvelcomicsapp.core.network.base.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/public/characters")
    suspend fun getCharactersResponse(
        @Query("name") name: String,
        @Query("ts") ts: String,
        @Query("apikey") key: String,
        @Query("hash") hash: String,
    ): Response<MarvelCharacterResponse>
}